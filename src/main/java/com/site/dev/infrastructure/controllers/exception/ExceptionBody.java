package com.site.dev.infrastructure.controllers.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ExceptionBody {
    public String message;
    public int statusCode;
}
