package com.site.dev.core.applications.usecases.coins;

import com.site.dev.core.applications.gateway.CoinsGateWay;
import com.site.dev.core.domain.entity.Coins;
import com.site.dev.core.domain.exception.NoDuplicateEntityException;

public class CreateCoinsUsecases {

    private CoinsGateWay coinsGateWay;

    public CreateCoinsUsecases(CoinsGateWay coinsGateWay) {
        this.coinsGateWay = coinsGateWay;
    }
    
    public Coins execute(Coins coins) {
        if(coinsGateWay.getByUUID(coins.getUuid()) != null) {
            throw new NoDuplicateEntityException("Banco/Money");
        }
        return coinsGateWay.create(coins.correct());
    }

}
