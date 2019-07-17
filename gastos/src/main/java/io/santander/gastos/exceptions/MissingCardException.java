package io.santander.gastos.exceptions;

public class MissingCardException extends RuntimeException {
    public MissingCardException(String message) {
        super(message);
    }
}
