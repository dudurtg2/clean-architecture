package com.site.dev.core.applications.usecases.coins;

import com.site.dev.core.applications.gateway.CoinsGateWay;
import com.site.dev.core.domain.entity.Coins;

public class CreateCoinsUsecases {
    private CoinsGateWay coinsGateWay;
    private ValidadeCoins validadeCoins ;

    public CreateCoinsUsecases(CoinsGateWay coinsGateWay) {
        this.coinsGateWay = coinsGateWay;
        validadeCoins = new ValidadeCoins();
       
    }

    public Coins execute(Coins coins) {
        validadeCoins.validateBory(coins);
        return coinsGateWay.create(coins);
    }

    
}
