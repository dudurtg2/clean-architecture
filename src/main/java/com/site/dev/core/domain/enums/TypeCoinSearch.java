package com.site.dev.core.domain.enums;

public enum TypeCoinSearch {
    SYMBOL(0),
    NAME(1),
   CATEGORY(2),
   CRYPTO(3),
   GOAL(4),
   ACTIVE(5);


    private final int value;
    
    TypeCoinSearch(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
}

