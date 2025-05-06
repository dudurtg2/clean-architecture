package com.site.dev.core.applications.usecases.coins;

import com.site.dev.core.applications.gateway.CoinsGateWay;

public class DeleteCoinsUsecases {
    private CoinsGateWay coinsGateWay;
    private ValidadeCoins validadeCoins;
    

    public DeleteCoinsUsecases(CoinsGateWay coinsGateWay) {
        this.coinsGateWay = coinsGateWay;
        this.validadeCoins = new ValidadeCoins();
    }

    public void execute(long id) {
        validadeCoins.verifyCoinsExists(id, coinsGateWay);
        coinsGateWay.delete(id);
    }
    
}
