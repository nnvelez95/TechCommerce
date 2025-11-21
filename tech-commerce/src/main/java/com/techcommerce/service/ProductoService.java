package com.techcommerce.service;

import com.techcommerce.model.Producto;
import com.techcommerce.repository.ProductoRepository;
import com.techcommerce.exception.ProductoNoEncontradoException;
import com.techcommerce.exception.StockInsuficienteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // 1. Obtener todos los productos
    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    // 2. Obtener producto por ID
    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException(id));
    }

    // 3. Crear nuevo producto
    public Producto crearProducto(Producto producto) {
        validarProducto(producto);

        // Verificar si ya existe un producto con el mismo nombre
        Optional<Producto> productoExistente = productoRepository.findByNombre(producto.getNombre());
        if (productoExistente.isPresent()) {
            throw new IllegalArgumentException("Ya existe un producto con el nombre: " + producto.getNombre());
        }

        return productoRepository.save(producto);
    }

    // 4. Actualizar producto
    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        Producto productoExistente = obtenerProductoPorId(id);

        // Validar que el nombre no esté duplicado (si cambió)
        if (!productoExistente.getNombre().equals(productoActualizado.getNombre())) {
            Optional<Producto> productoConMismoNombre = productoRepository.findByNombre(productoActualizado.getNombre());
            if (productoConMismoNombre.isPresent()) {
                throw new IllegalArgumentException("Ya existe un producto con el nombre: " + productoActualizado.getNombre());
            }
        }

        // Actualizar campos
        productoExistente.setNombre(productoActualizado.getNombre());
        productoExistente.setDescripcion(productoActualizado.getDescripcion());
        productoExistente.setPrecio(productoActualizado.getPrecio());
        productoExistente.setCategoria(productoActualizado.getCategoria());
        productoExistente.setImagenUrl(productoActualizado.getImagenUrl());
        productoExistente.setStock(productoActualizado.getStock());

        validarProducto(productoExistente);
        return productoRepository.save(productoExistente);
    }

    // 5. Eliminar producto
    public void eliminarProducto(Long id) {
        Producto producto = obtenerProductoPorId(id);
        productoRepository.delete(producto);
    }

    // 6. Buscar productos por nombre
    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    // 7. Buscar productos por categoría
    public List<Producto> buscarPorCategoria(String categoria) {
        return productoRepository.findByCategoriaContainingIgnoreCase(categoria);
    }

    // 8. Búsqueda avanzada (nombre o categoría)
    public List<Producto> buscarPorNombreOCategoria(String termino) {
        return productoRepository.buscarPorNombreOCategoria(termino);
    }

    // 9. Obtener productos con stock bajo
    public List<Producto> obtenerProductosConStockBajo(Integer stockMinimo) {
        return productoRepository.findByStockLessThan(stockMinimo);
    }

    // 10. Actualizar stock (para pedidos)
    public void actualizarStock(Long productoId, Integer cantidad) {
        Producto producto = obtenerProductoPorId(productoId);

        int nuevoStock = producto.getStock() - cantidad;
        if (nuevoStock < 0) {
            throw new StockInsuficienteException(
                    producto.getNombre(),
                    producto.getStock(),
                    cantidad
            );
        }

        producto.setStock(nuevoStock);
        productoRepository.save(producto);
    }

    // 11. Validaciones del producto
    private void validarProducto(Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio");
        }
        if (producto.getPrecio() == null || producto.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        if (producto.getStock() == null || producto.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
    }

    // 12. Verificar disponibilidad de stock
    public boolean verificarDisponibilidadStock(Long productoId, Integer cantidadSolicitada) {
        Producto producto = obtenerProductoPorId(productoId);
        return producto.getStock() >= cantidadSolicitada;
    }
}