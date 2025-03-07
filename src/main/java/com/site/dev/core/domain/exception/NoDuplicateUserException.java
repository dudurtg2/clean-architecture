package com.site.dev.core.domain.exception;

public class NoDuplicateUserException extends RuntimeException {

    private final int statusCode;

    public NoDuplicateUserException() {
        super("User não é valido");
        this.statusCode = 400;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
