package com.site.dev.adapter.entity;

import java.time.LocalDateTime;

import com.site.dev.core.domain.enums.TypeCoins;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import java.util.Objects;



@Entity
@Table(name = "movements")
@Data
@Builder
public class MovementsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "value")
    private Float value;

    @Column(name = "price")
    private Float price;

    @Column(name = "type_coins")
    private TypeCoins typeCoins;

    @ManyToOne
    @JoinColumn(name = "coins_id")
    private CoinsEntity coins;


    public MovementsEntity() {
    }

    public MovementsEntity(Long id, LocalDateTime date, Float value, Float price, TypeCoins typeCoins, CoinsEntity coins) {
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

    public CoinsEntity getCoins() {
        return this.coins;
    }

    public void setCoins(CoinsEntity coins) {
        this.coins = coins;
    }

   
    
}
