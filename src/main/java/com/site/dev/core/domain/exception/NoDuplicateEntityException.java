package com.site.dev.core.domain.exception;

public class NoDuplicateEntityException extends RuntimeException {
    private final int statusCode;

    public NoDuplicateEntityException(String entityName) {
        super(entityName + " já cadastrado");
        this.statusCode = 400;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
