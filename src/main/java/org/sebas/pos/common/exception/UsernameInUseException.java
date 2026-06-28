package org.sebas.pos.common.exception;

public class UsernameInUseException extends RuntimeException {
    public UsernameInUseException(String message) {
        super(message);
    }

    public UsernameInUseException(String message, Throwable cause) {
        super(message, cause);
    }
}

