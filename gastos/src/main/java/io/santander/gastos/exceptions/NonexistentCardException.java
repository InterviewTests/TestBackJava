package io.santander.gastos.exceptions;

public class NonexistentCardException extends RuntimeException {
    public NonexistentCardException(String message) {
        super(message);
    }
}
