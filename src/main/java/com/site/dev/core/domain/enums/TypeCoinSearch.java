package com.site.dev.core.domain.enums;

public enum TypeCoinSearch {
    SYMBOL(0),
    NAME(1);


    private final int value;
    
    TypeCoinSearch(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
}

