package com.site.dev.core.domain.exception;

public class CustomException extends RuntimeException {

    private final int statusCode;

    public CustomException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
