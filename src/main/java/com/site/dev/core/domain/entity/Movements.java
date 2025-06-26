package com.site.dev.core.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.site.dev.core.domain.enums.TipoDespesa;
import com.site.dev.core.domain.exception.IncorrectBodyException;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Movements {

    private UUID uuid;
    private LocalDateTime date;
    private Float value;
    private Float price;
    private TipoDespesa tipoDespesa;
    private String description;
    private Coins coins;

    public Movements correct() {
        if (date == null
                || value == null
                || price == null
                || tipoDespesa == null
                || coins == null) {
            throw new IncorrectBodyException();
        }
    
        return this;
    }
}
