package com.site.dev.core.applications.usecases.coins;

import com.site.dev.core.applications.gateway.CoinsGateWay;
import com.site.dev.core.domain.entity.Coins;

public class CreateCoinsUsecases {

    private CoinsGateWay coinsGateWay;

    public CreateCoinsUsecases(CoinsGateWay coinsGateWay) {
        this.coinsGateWay = coinsGateWay;
    }
    
    public Coins execute(Coins coins) {
        return coinsGateWay.create(coins.correct());
    }

}
