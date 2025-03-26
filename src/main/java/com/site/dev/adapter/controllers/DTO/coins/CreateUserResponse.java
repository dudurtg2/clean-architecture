package com.site.dev.adapter.controllers.DTO.coins;

import lombok.Builder;

@Builder
public record CreateUserResponse(String name, String symbol, String image, Float totalValue, Float inputValue, Float outputValue) {
    
}
