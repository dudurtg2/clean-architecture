package com.site.dev.core.applications.usecases.coins;

import com.site.dev.core.applications.gateway.CoinsGateWay;
import com.site.dev.core.domain.entity.Coins;
import com.site.dev.core.domain.exception.IncorretBoryUserException;
public class CreateCoinsUsecases {
    private CoinsGateWay coinsGateWay;

    public CreateCoinsUsecases(CoinsGateWay coinsGateWay) {
        this.coinsGateWay = coinsGateWay;
    }

    public Coins execute(Coins coins) {
        validate(coins);
        return coinsGateWay.create(coins);
    }

    private void validate(Coins coins) {
        if (coins.getName() == null || coins.getSymbol() == null || coins.getImage() == null) throw new IncorretBoryUserException();
    } 
}
