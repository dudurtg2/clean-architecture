package com.site.dev.core.domain.entity;

import java.time.LocalDateTime;

import com.site.dev.core.domain.enums.TypeCoins;
import com.site.dev.core.domain.exception.IncorrectBodyException;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class Movements {

    private Long id;
    private LocalDateTime date;
    private Float value;
    private Float price;
    private TypeCoins typeCoins;
    private Coins coins;

    public Movements correct() {
        if (date == null
                || value == null
                || price == null
                || typeCoins == null
                || coins == null) {
            throw new IncorrectBodyException();
        }
        if (value <= 0 || price < 0) {
            throw new IncorrectBodyException();
        }
        return this;
    }
}
