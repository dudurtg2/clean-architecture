package com.site.dev.core.applications.usecases.coins;

import com.site.dev.core.applications.gateway.CoinsGateWay;
import com.site.dev.core.domain.entity.Coins;

public class UpdateCoinsUsecases {
    private CoinsGateWay coinsGateWay;
    private ValidadeCoins validadeCoins;

    public UpdateCoinsUsecases(CoinsGateWay coinsGateWay) {
        this.coinsGateWay = coinsGateWay;
        this.validadeCoins = new ValidadeCoins();
    }

    public Coins execute(Long id, Coins coins) {
        validadeCoins.verifyCoinsExists(id, coinsGateWay);
        validadeCoins.validateBory(coins);
        return coinsGateWay.update(coins);
    }


}
