package com.techcommerce.repository;

import com.techcommerce.model.Pedido;
import com.techcommerce.model.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Buscar pedidos por usuario
    List<Pedido> findByUsuarioId(Long usuarioId);

    // Buscar pedidos por estado
    List<Pedido> findByEstado(EstadoPedido estado);

    // Buscar pedidos por usuario y estado
    List<Pedido> findByUsuarioIdAndEstado(Long usuarioId, EstadoPedido estado);

    // Buscar pedidos en un rango de fechas
    List<Pedido> findByFechaCreacionBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    // Obtener pedidos recientes (últimos 7 días)
    @Query("SELECT p FROM Pedido p WHERE p.fechaCreacion >= :fecha")
    List<Pedido> findPedidosRecientes(@Param("fecha") LocalDateTime fecha);

    // Contar pedidos por estado
    long countByEstado(EstadoPedido estado);
}