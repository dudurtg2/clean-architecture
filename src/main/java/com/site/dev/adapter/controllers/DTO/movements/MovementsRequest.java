package com.site.dev.adapter.controllers.dto.movements;

import com.site.dev.core.domain.entity.Coins;
import com.site.dev.core.domain.enums.TipoDespesa;
import lombok.*;

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
    private String description;
    private TipoDespesa tipoDespesa;
    private Coins coins;

}
