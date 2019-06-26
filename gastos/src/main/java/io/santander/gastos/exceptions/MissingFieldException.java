package io.santander.gastos.exceptions;

public class MissingFieldException extends RuntimeException {
    private static final long serialVersionUID = 6210964506339092884L;

    public MissingFieldException(final String message) {
        super(message);
    }
}
