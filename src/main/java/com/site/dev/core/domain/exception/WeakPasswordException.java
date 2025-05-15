package com.site.dev.core.domain.exception;

public class WeakPasswordException extends RuntimeException {
    private final int statusCode;

    public WeakPasswordException() {
        super("Senha deve conter mais de 8 caracteres");

        this.statusCode = 400;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
