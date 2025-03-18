package com.site.dev.adapter.controllers.DTO.users;

import lombok.Builder;

@Builder
public record CreateUserResponse(String name, String email) {
    
}
