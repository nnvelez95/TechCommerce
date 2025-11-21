package com.techcommerce.service;

import com.techcommerce.model.*;
import com.techcommerce.repository.PedidoRepository;
import com.techcommerce.repository.ProductoRepository;
import com.techcommerce.exception.PedidoNoEncontradoException;
import com.techcommerce.exception.StockInsuficienteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoService productoService;

    // 1. Crear nuevo pedido
    public Pedido crearPedido(Long usuarioId, Map<Long, Integer> itemsPedido) {
        Pedido pedido = new Pedido(usuarioId);

        // Validar y agregar items al pedido
        for (Map.Entry<Long, Integer> entry : itemsPedido.entrySet()) {
            Long productoId = entry.getKey();
            Integer cantidad = entry.getValue();

            // Verificar disponibilidad
            if (!productoService.verificarDisponibilidadStock(productoId, cantidad)) {
                throw new StockInsuficienteException(
                        productoService.obtenerProductoPorId(productoId).getNombre(),
                        productoService.obtenerProductoPorId(productoId).getStock(),
                        cantidad
                );
            }

            // Crear línea de pedido
            LineaPedido linea = new LineaPedido(
                    productoService.obtenerProductoPorId(productoId),
                    cantidad
            );

            pedido.agregarItem(linea);
        }

        // Si el pedido puede confirmarse, actualizar stocks
        if (pedido.puedeConfirmarse()) {
            confirmarPedido(pedido);
        }

        return pedidoRepository.save(pedido);
    }

    // 2. Obtener pedido por ID
    public Pedido obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNoEncontradoException(id));
    }

    // 3. Obtener pedidos por usuario
    public List<Pedido> obtenerPedidosPorUsuario(Long usuarioId) {
        return pedidoRepository.findByUsuarioId(usuarioId);
    }

    // 4. Obtener todos los pedidos
    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoRepository.findAll();
    }

    // 5. Actualizar estado del pedido
    public Pedido actualizarEstadoPedido(Long pedidoId, EstadoPedido nuevoEstado) {
        Pedido pedido = obtenerPedidoPorId(pedidoId);

        // Validar transición de estado
        validarTransicionEstado(pedido.getEstado(), nuevoEstado);

        pedido.setEstado(nuevoEstado);
        return pedidoRepository.save(pedido);
    }

    // 6. Cancelar pedido
    public Pedido cancelarPedido(Long pedidoId) {
        Pedido pedido = obtenerPedidoPorId(pedidoId);

        // Solo se pueden cancelar pedidos pendientes o confirmados
        if (pedido.getEstado() != EstadoPedido.PENDIENTE &&
                pedido.getEstado() != EstadoPedido.CONFIRMADO) {
            throw new IllegalArgumentException(
                    "No se puede cancelar un pedido en estado: " + pedido.getEstado()
            );
        }

        // Si estaba confirmado, devolver stock
        if (pedido.getEstado() == EstadoPedido.CONFIRMADO) {
            devolverStock(pedido);
        }

        pedido.setEstado(EstadoPedido.CANCELADO);
        return pedidoRepository.save(pedido);
    }

    // 7. Confirmar pedido (actualizar stocks)
    private void confirmarPedido(Pedido pedido) {
        for (LineaPedido item : pedido.getItems()) {
            productoService.actualizarStock(
                    item.getProducto().getId(),
                    item.getCantidad()
            );
        }
        pedido.setEstado(EstadoPedido.CONFIRMADO);
    }

    // 8. Devolver stock al cancelar
    private void devolverStock(Pedido pedido) {
        for (LineaPedido item : pedido.getItems()) {
            Producto producto = item.getProducto();
            producto.setStock(producto.getStock() + item.getCantidad());
            // Aquí podrías usar productoRepository.save(producto) si no tienes cascade
        }
    }

    // 9. Validar transición de estado
    private void validarTransicionEstado(EstadoPedido estadoActual, EstadoPedido nuevoEstado) {
        // Lógica de validación de transiciones de estado
        // Por ejemplo: no se puede pasar de CANCELADO a CONFIRMADO
        if (estadoActual == EstadoPedido.CANCELADO && nuevoEstado != EstadoPedido.CANCELADO) {
            throw new IllegalArgumentException("Un pedido cancelado no puede cambiar de estado");
        }

        if (estadoActual == EstadoPedido.ENTREGADO && nuevoEstado != EstadoPedido.ENTREGADO) {
            throw new IllegalArgumentException("Un pedido entregado no puede cambiar de estado");
        }
    }

    // 10. Obtener pedidos por estado
    public List<Pedido> obtenerPedidosPorEstado(EstadoPedido estado) {
        return pedidoRepository.findByEstado(estado);
    }

    // 11. Obtener pedidos recientes (últimos 7 días)
    public List<Pedido> obtenerPedidosRecientes() {
        LocalDateTime hace7Dias = LocalDateTime.now().minusDays(7);
        return pedidoRepository.findPedidosRecientes(hace7Dias);
    }

    // 12. Estadísticas de pedidos
    public Map<EstadoPedido, Long> obtenerEstadisticasPedidos() {
        return Map.of(
                EstadoPedido.PENDIENTE, pedidoRepository.countByEstado(EstadoPedido.PENDIENTE),
                EstadoPedido.CONFIRMADO, pedidoRepository.countByEstado(EstadoPedido.CONFIRMADO),
                EstadoPedido.ENVIADO, pedidoRepository.countByEstado(EstadoPedido.ENVIADO),
                EstadoPedido.ENTREGADO, pedidoRepository.countByEstado(EstadoPedido.ENTREGADO),
                EstadoPedido.CANCELADO, pedidoRepository.countByEstado(EstadoPedido.CANCELADO)
        );
    }
}