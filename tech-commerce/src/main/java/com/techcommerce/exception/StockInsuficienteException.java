package com.techcommerce.exception;

public class StockInsuficienteException extends RuntimeException {
    public StockInsuficienteException(String mensaje) {
        super(mensaje);
    }

    public StockInsuficienteException(String nombreProducto, Integer stockDisponible, Integer stockSolicitado) {
        super(String.format("Stock insuficiente para el producto '%s'. Disponible: %d, Solicitado: %d",
                nombreProducto, stockDisponible, stockSolicitado));
    }
}