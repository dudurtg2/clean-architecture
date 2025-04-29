package com.site.dev.adapter.controllers.DTO.movements;

import java.time.LocalDateTime;

import com.site.dev.core.domain.enums.TypeCoins;

import lombok.Builder;

@Builder
public record CreateMovementsResponse(LocalDateTime date, Float value, Float price, TypeCoins typeCoins, Long coinsId) {
    
}
