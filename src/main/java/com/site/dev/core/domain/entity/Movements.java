package com.site.dev.core.domain.entity;

import java.time.LocalDateTime;

import com.site.dev.core.domain.enums.TypeCoins;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movements {
    LocalDateTime date;
    Float value;
    Float price;
    TypeCoins typeCoins;
    Coins coins;

    public String getCoins() {
        return coins.name;
    }
}
