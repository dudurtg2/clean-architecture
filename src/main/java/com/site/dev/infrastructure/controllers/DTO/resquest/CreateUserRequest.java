package com.site.dev.infrastructure.controllers.DTO.resquest;

import lombok.Builder;

@Builder
public record CreateUserRequest (String name, String email, String password) {
    
}
