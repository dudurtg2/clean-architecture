package com.site.dev.adapter.controllers.dto.movements;

import com.site.dev.core.domain.entity.Coins;
import com.site.dev.core.domain.enums.TypeCoins;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class MovementsRequest {


    private UUID uuid;
    private Float value;
    private Float price;
    private TypeCoins typeCoins;
    private Coins coins;

}
