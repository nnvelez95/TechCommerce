package com.techcommerce.model;



import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPedido estado;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LineaPedido> items = new ArrayList<>();

    // Constructores
    public Pedido() {
        this.fechaCreacion = LocalDateTime.now();
        this.estado = EstadoPedido.PENDIENTE;
    }

    public Pedido(Long usuarioId) {
        this();
        this.usuarioId = usuarioId;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public EstadoPedido getEstado() { return estado; }
    public void setEstado(EstadoPedido estado) { this.estado = estado; }

    public List<LineaPedido> getItems() { return items; }
    public void setItems(List<LineaPedido> items) { this.items = items; }

    // Métodos de negocio
    public void agregarItem(LineaPedido item) {
        item.setPedido(this);
        this.items.add(item);
    }

    public Double getTotal() {
        return items.stream()
                .mapToDouble(LineaPedido::getSubtotal)
                .sum();
    }

    // Método para verificar si se puede confirmar (stock suficiente)
    public boolean puedeConfirmarse() {
        return items.stream().allMatch(item ->
                item.getProducto().getStock() >= item.getCantidad()
        );
    }
}