package com.site.dev.adapter.controllers.DTO.response;

import lombok.Builder;

@Builder
public record CreateUserResponse(String name, String email) {
    
}
