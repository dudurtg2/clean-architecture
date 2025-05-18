package com.site.dev.core.domain.entity;

import com.site.dev.core.domain.exception.IncorrectBodyException;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Goals {

    private UUID uuid;
    private float goal;
    private String description;
    private Users user;
    private Coins coins;
    private LocalDate data;


    public Goals correct() {
        if (goal < 0 ||
            description == null ||
            coins == null ||
            data == null) {
            throw new IncorrectBodyException();
        }
        if (user == null) {
            throw new IncorrectBodyException();
        }
        return this;
    }
}
