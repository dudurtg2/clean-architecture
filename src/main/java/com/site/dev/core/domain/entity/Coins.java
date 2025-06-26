package com.site.dev.core.domain.entity;
import com.site.dev.core.domain.exception.IncorrectBodyException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Coins {

    private UUID uuid;
    private String name;
    private String symbol;
    private String category;
    private String description;
    private String color;
    private String subcategory;
    private String image;
    private Users user;

    public Coins correct() {
        if (name == null || name.isBlank()
                || category == null || category.isBlank()
                || symbol == null || symbol.isBlank()
                || image == null || image.isBlank()) {
            throw new IncorrectBodyException();
        }
        if (user == null) {
            throw new IncorrectBodyException();
        }
        return this;
    }
}
