package com.site.dev.adapter.controllers.DTO.coins;

import lombok.Builder;

@Builder
public record CreateUserRequest(String name, String symbol, String image, Long userId) {
    
}
