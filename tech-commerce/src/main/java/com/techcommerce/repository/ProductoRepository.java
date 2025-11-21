package com.techcommerce.repository;

import com.techcommerce.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Buscar productos por nombre (ignorando mayúsculas/minúsculas)
    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    // Buscar productos por categoría (ignorando mayúsculas/minúsculas)
    List<Producto> findByCategoriaContainingIgnoreCase(String categoria);

    // Buscar producto por nombre exacto
    Optional<Producto> findByNombre(String nombre);

    // Buscar productos con stock bajo (para alertas)
    List<Producto> findByStockLessThan(Integer stock);

    // Búsqueda avanzada por nombre o categoría
    @Query("SELECT p FROM Producto p WHERE " +
            "LOWER(p.nombre) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
            "LOWER(p.categoria) LIKE LOWER(CONCAT('%', :termino, '%'))")
    List<Producto> buscarPorNombreOCategoria(@Param("termino") String termino);

    // Encontrar productos por rango de precio
    List<Producto> findByPrecioBetween(Double precioMin, Double precioMax);
}