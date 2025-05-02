package com.site.dev.core.domain.entity;

import java.time.LocalDateTime;

import com.site.dev.core.domain.enums.TypeCoins;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Movements {
    private Long id;
    private LocalDateTime date;
    private Float value;
    private Float price;
    private TypeCoins typeCoins;
    private Coins coins;
}
