package org.sebas.pos.exception;

public class NoStockException extends RuntimeException {
    public NoStockException(String message) {
        super(message);
    }
}
