package com.site.dev.core.applications.usecases.coins;

import com.site.dev.core.applications.gateway.CoinsGateWay;
import com.site.dev.core.domain.exception.NotExistsEntityException;

public class DeleteCoinsUsecases {
    private CoinsGateWay coinsGateWay;
    
    public DeleteCoinsUsecases(CoinsGateWay coinsGateWay) {
        this.coinsGateWay = coinsGateWay;
    }
    
    public void execute(long id) {
        if(coinsGateWay.getById(id) == null) throw new NotExistsEntityException("Coins");
        coinsGateWay.delete(id);
    }
    
}
