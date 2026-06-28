package org.sebas.pos.common.exception;

public class CartEmptyExcepcion extends RuntimeException {
    public CartEmptyExcepcion(String message) {
        super(message);
    }

    public CartEmptyExcepcion(String message, Throwable cause) {
        super(message, cause);
    }
}

