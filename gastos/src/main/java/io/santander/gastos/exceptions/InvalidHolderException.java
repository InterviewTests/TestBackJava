package io.santander.gastos.exceptions;

public class InvalidHolderException extends RuntimeException {
    public InvalidHolderException(String message) {
        super(message);
    }
}
