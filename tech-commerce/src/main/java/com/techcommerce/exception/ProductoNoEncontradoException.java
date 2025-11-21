package com.techcommerce.exception;

public class ProductoNoEncontradoException extends RuntimeException {
    public ProductoNoEncontradoException(String mensaje) {
        super(mensaje);
    }

    public ProductoNoEncontradoException(Long id) {
        super("Producto no encontrado con ID: " + id);
    }
}