package com.techcommerce.controller;

import com.techcommerce.model.Producto;
import com.techcommerce.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:3000") // Para el frontend después
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // 1. Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> obtenerTodosLosProductos() {
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        return ResponseEntity.ok(productos);
    }

    // 2. Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        Producto producto = productoService.obtenerProductoPorId(id);
        return ResponseEntity.ok(producto);
    }

    // 3. Crear nuevo producto
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.crearProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    // 4. Actualizar producto
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Producto productoActualizado = productoService.actualizarProducto(id, producto);
        return ResponseEntity.ok(productoActualizado);
    }

    // 5. Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    // 6. Búsqueda flexible por nombre o categoría
    @GetMapping("/buscar")
    public ResponseEntity<List<Producto>> buscarProductos(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String termino) {

        // Búsqueda por término general (nombre o categoría)
        if (termino != null && !termino.trim().isEmpty()) {
            List<Producto> productos = productoService.buscarPorNombreOCategoria(termino);
            return ResponseEntity.ok(productos);
        }

        // Búsqueda específica por nombre
        if (nombre != null && !nombre.trim().isEmpty()) {
            List<Producto> productos = productoService.buscarPorNombre(nombre);
            return ResponseEntity.ok(productos);
        }

        // Búsqueda específica por categoría
        if (categoria != null && !categoria.trim().isEmpty()) {
            List<Producto> productos = productoService.buscarPorCategoria(categoria);
            return ResponseEntity.ok(productos);
        }

        // Si no hay parámetros, devolver todos los productos
        return ResponseEntity.ok(productoService.obtenerTodosLosProductos());
    }

    // 7. Obtener productos con stock bajo
    @GetMapping("/stock-bajo")
    public ResponseEntity<List<Producto>> obtenerProductosConStockBajo(@RequestParam(defaultValue = "10") Integer stockMinimo) {
        List<Producto> productos = productoService.obtenerProductosConStockBajo(stockMinimo);
        return ResponseEntity.ok(productos);
    }

    // 8. Verificar disponibilidad de stock
    @GetMapping("/{id}/disponibilidad")
    public ResponseEntity<Map<String, Object>> verificarDisponibilidad(
            @PathVariable Long id,
            @RequestParam Integer cantidad) {

        boolean disponible = productoService.verificarDisponibilidadStock(id, cantidad);
        Producto producto = productoService.obtenerProductoPorId(id);

        Map<String, Object> respuesta = Map.of(
                "productoId", id,
                "productoNombre", producto.getNombre(),
                "cantidadSolicitada", cantidad,
                "stockDisponible", producto.getStock(),
                "disponible", disponible
        );

        return ResponseEntity.ok(respuesta);
    }
}