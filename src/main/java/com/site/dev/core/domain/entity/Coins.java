package com.site.dev.core.domain.entity;


import lombok.Builder;
import lombok.Data;
import java.util.Objects;


@Data
@Builder
public class Coins {
    private Long id;
    private String name;
    private String symbol;
    private String image;
    private Users user;

    public Coins() {
    }

    public Coins(Long id, String name, String symbol, String image, Users user) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.image = image;
        this.user = user;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Users getUser() {
        return this.user;
    }

    public void setUser(Users user) {
        this.user = user;
    }


    
}
