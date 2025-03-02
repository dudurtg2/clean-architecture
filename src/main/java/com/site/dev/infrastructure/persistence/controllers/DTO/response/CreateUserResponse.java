package com.site.dev.infrastructure.persistence.controllers.DTO.response;

import lombok.Builder;

@Builder
public record CreateUserResponse(String name, String email) {
    
}
