package com.site.dev.core.domain.exception;

public class NotExistsEntityException extends RuntimeException {
    private final int statusCode;

    public NotExistsEntityException(String entity) {
        super(entity + " não é valido");
        this.statusCode = 404;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
