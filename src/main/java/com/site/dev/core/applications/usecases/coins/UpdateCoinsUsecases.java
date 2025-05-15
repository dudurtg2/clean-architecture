package com.site.dev.core.applications.usecases.coins;

import com.site.dev.core.applications.gateway.CoinsGateWay;
import com.site.dev.core.domain.entity.Coins;
import com.site.dev.core.domain.exception.NotExistsEntityException;

import java.util.UUID;

public class UpdateCoinsUsecases {
    private CoinsGateWay coinsGateWay;

    public UpdateCoinsUsecases(CoinsGateWay coinsGateWay) {
        this.coinsGateWay = coinsGateWay;
    }
    public Coins execute(String uuid, Coins coins) {
        if(coinsGateWay.getByUUID(uuid) == null) throw new NotExistsEntityException("Coins");
        validateNewBory(coins);
        return coinsGateWay.update(coins);
    }
    public void validateNewBory(Coins coins) {
        Coins coinsInBD = coinsGateWay.getByUUID(coins.getUuid());
        coins.setImage(coins.getImage() == null ? coinsInBD.getImage() : coins.getImage());
        coins.setName(coins.getName() == null ? coinsInBD.getName() : coins.getName());
        coins.setSymbol(coins.getSymbol() == null ? coinsInBD.getSymbol() : coins.getSymbol());
        coins.setUser(coins.getUser() == null ? coinsInBD.getUser() : coins.getUser());
    }

}
