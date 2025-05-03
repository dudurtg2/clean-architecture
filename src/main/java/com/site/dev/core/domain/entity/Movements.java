package com.site.dev.core.domain.entity;

import java.time.LocalDateTime;

import com.site.dev.core.domain.enums.TypeCoins;

import lombok.Builder;
import lombok.Data;
import java.util.Objects;


@Data
@Builder
public class Movements {
    private Long id;
    private LocalDateTime date;
    private Float value;
    private Float price;
    private TypeCoins typeCoins;
    private Coins coins;

    public Movements() {
    }

    public Movements(Long id, LocalDateTime date, Float value, Float price, TypeCoins typeCoins, Coins coins) {
        this.id = id;
        this.date = date;
        this.value = value;
        this.price = price;
        this.typeCoins = typeCoins;
        this.coins = coins;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Float getValue() {
        return this.value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public TypeCoins getTypeCoins() {
        return this.typeCoins;
    }

    public void setTypeCoins(TypeCoins typeCoins) {
        this.typeCoins = typeCoins;
    }

    public Coins getCoins() {
        return this.coins;
    }

    public void setCoins(Coins coins) {
        this.coins = coins;
    }

   

}
