package com.site.dev.adapter.controllers.DTO.users;

import lombok.Builder;

@Builder
public record UsersResponse(String name, String email) {
    
}
