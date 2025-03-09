package com.site.dev.core.domain.exception;

public class IncorretBoryUserException extends RuntimeException {
    private final int statusCode;

    public IncorretBoryUserException() {
        super("User não é valido");
        this.statusCode = 400;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
