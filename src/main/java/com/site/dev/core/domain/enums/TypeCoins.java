package com.site.dev.core.domain.enums;

public enum TypeCoins {
    CRIPTO(0),
    BANK(1),
    INVESTMENTS(2),
    MONEY(3),
    PHYSICAL(4),
    OTHER(5);


    private final int value;
    
    TypeCoins(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
}

