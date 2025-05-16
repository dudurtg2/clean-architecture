package com.site.dev.core.applications.usecases.coins;

import com.site.dev.core.applications.gateway.CoinsGateWay;
import com.site.dev.core.domain.exception.NotExistsEntityException;

import java.util.UUID;

public class DeleteCoinsUsecases {
    private CoinsGateWay coinsGateWay;
    
    public DeleteCoinsUsecases(CoinsGateWay coinsGateWay) {
        this.coinsGateWay = coinsGateWay;
    }
    
    public void execute(UUID uuid) {
        if(coinsGateWay.getByUUID(uuid) == null) throw new NotExistsEntityException("Coins");
        coinsGateWay.delete(uuid);
    }
    
}
