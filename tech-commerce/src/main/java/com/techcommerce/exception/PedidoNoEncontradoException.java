package com.techcommerce.exception;

public class PedidoNoEncontradoException extends RuntimeException {
    public PedidoNoEncontradoException(String mensaje) {
        super(mensaje);
    }

    public PedidoNoEncontradoException(Long id) {
        super("Pedido no encontrado con ID: " + id);
    }
}