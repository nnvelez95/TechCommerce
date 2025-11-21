package com.techcommerce.controller;

import com.techcommerce.model.EstadoPedido;
import com.techcommerce.model.Pedido;
import com.techcommerce.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "http://localhost:3000")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // DTO para crear pedidos
    public static class CrearPedidoRequest {
        private Long usuarioId;
        private Map<Long, Integer> items; // productoId -> cantidad

        // Getters y Setters
        public Long getUsuarioId() { return usuarioId; }
        public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
        public Map<Long, Integer> getItems() { return items; }
        public void setItems(Map<Long, Integer> items) { this.items = items; }
    }

    // 1. Crear nuevo pedido
    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody CrearPedidoRequest request) {
        Pedido nuevoPedido = pedidoService.crearPedido(request.getUsuarioId(), request.getItems());
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
    }

    // 2. Obtener todos los pedidos
    @GetMapping
    public ResponseEntity<List<Pedido>> obtenerTodosLosPedidos() {
        List<Pedido> pedidos = pedidoService.obtenerTodosLosPedidos();
        return ResponseEntity.ok(pedidos);
    }

    // 3. Obtener pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPedidoPorId(@PathVariable Long id) {
        Pedido pedido = pedidoService.obtenerPedidoPorId(id);
        return ResponseEntity.ok(pedido);
    }

    // 4. Obtener pedidos por usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Pedido>> obtenerPedidosPorUsuario(@PathVariable Long usuarioId) {
        List<Pedido> pedidos = pedidoService.obtenerPedidosPorUsuario(usuarioId);
        return ResponseEntity.ok(pedidos);
    }

    // 5. Actualizar estado del pedido
    @PutMapping("/{id}/estado")
    public ResponseEntity<Pedido> actualizarEstadoPedido(
            @PathVariable Long id,
            @RequestParam EstadoPedido estado) {

        Pedido pedidoActualizado = pedidoService.actualizarEstadoPedido(id, estado);
        return ResponseEntity.ok(pedidoActualizado);
    }

    // 6. Cancelar pedido
    @PostMapping("/{id}/cancelar")
    public ResponseEntity<Pedido> cancelarPedido(@PathVariable Long id) {
        Pedido pedidoCancelado = pedidoService.cancelarPedido(id);
        return ResponseEntity.ok(pedidoCancelado);
    }

    // 7. Obtener pedidos por estado
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Pedido>> obtenerPedidosPorEstado(@PathVariable EstadoPedido estado) {
        List<Pedido> pedidos = pedidoService.obtenerPedidosPorEstado(estado);
        return ResponseEntity.ok(pedidos);
    }

    // 8. Obtener pedidos recientes
    @GetMapping("/recientes")
    public ResponseEntity<List<Pedido>> obtenerPedidosRecientes() {
        List<Pedido> pedidos = pedidoService.obtenerPedidosRecientes();
        return ResponseEntity.ok(pedidos);
    }

    // 9. Obtener estadísticas de pedidos
    @GetMapping("/estadisticas")
    public ResponseEntity<Map<EstadoPedido, Long>> obtenerEstadisticasPedidos() {
        Map<EstadoPedido, Long> estadisticas = pedidoService.obtenerEstadisticasPedidos();
        return ResponseEntity.ok(estadisticas);
    }
}