package com.site.dev.adapter.controllers.DTO.users;

import lombok.Builder;

@Builder
public record UsersRequest (String name, String email, String password) {
    
}
