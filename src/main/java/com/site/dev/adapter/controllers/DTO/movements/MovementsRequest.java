package com.site.dev.adapter.controllers.dto.movements;

import java.time.LocalDateTime;

import com.site.dev.adapter.models.CoinsEntity;
import com.site.dev.core.domain.enums.TypeCoins;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class MovementsRequest {
    private LocalDateTime date;
    private Float value;
    private Float price;
    private TypeCoins typeCoins;
    private CoinsEntity coins;
}
