package com.site.dev.core.domain.exception;

public class IncorrectBodyException extends RuntimeException {
    private final int statusCode;

    public IncorrectBodyException() {
        super("Corpo não é valido");
        this.statusCode = 400;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
