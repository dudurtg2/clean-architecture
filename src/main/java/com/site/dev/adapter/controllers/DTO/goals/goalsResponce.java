package com.site.dev.adapter.controllers.dto.goals;

import com.site.dev.core.domain.entity.Coins;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class GoalsResponce {
    private UUID uuid;
    private float goal;
    private String description;
    private Coins coins;
    private LocalDate data;

}
