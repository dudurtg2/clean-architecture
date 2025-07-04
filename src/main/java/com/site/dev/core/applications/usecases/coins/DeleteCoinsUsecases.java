package com.site.dev.core.applications.usecases.coins;

import com.site.dev.core.applications.gateway.CoinsGateWay;
import com.site.dev.core.applications.gateway.MovementsGateWay;
import com.site.dev.core.domain.entity.Movements;
import com.site.dev.core.domain.exception.NotExistsEntityException;

import java.util.UUID;

public class DeleteCoinsUsecases {
    private CoinsGateWay coinsGateWay;
    private MovementsGateWay movementsGateWay;
    
    public DeleteCoinsUsecases(CoinsGateWay coinsGateWay, MovementsGateWay movementsGateWay) {
        this.coinsGateWay = coinsGateWay;
        this.movementsGateWay = movementsGateWay;
    }
    
    public void execute(UUID uuid) {
        if(coinsGateWay.getByUUID(uuid) == null) throw new NotExistsEntityException("Coins");
        for (Movements movement : movementsGateWay.getByCoins(coinsGateWay.getByUUID(uuid))) {
            movementsGateWay.delete(movement.getUuid());
        }
        coinsGateWay.delete(uuid);
    }
    
}
