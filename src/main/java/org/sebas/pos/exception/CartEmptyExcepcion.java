package org.sebas.pos.exception;

public class CartEmptyExcepcion extends RuntimeException {
    public CartEmptyExcepcion(String message) {
        super(message);
    }
}
