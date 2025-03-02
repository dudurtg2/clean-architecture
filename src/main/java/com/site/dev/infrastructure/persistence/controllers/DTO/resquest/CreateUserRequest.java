package com.site.dev.infrastructure.persistence.controllers.DTO.resquest;

import lombok.Builder;

@Builder
public record CreateUserRequest (String name, String email, String password) {
    
}
