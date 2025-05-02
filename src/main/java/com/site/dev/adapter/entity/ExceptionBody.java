package com.site.dev.adapter.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionBody {
    public String message;
    public int statusCode;


    public ExceptionBody(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public ExceptionBody() {
        
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

}
