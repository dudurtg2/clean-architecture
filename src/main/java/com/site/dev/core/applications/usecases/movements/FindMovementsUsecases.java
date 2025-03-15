package com.site.dev.core.applications.usecases.movements;

import java.util.List;

import com.site.dev.core.applications.gateway.MovementsGateWay;
import com.site.dev.core.domain.entity.Coins;
import com.site.dev.core.domain.entity.Movements;
import com.site.dev.core.domain.enums.TypeCoins;
public class FindMovementsUsecases {
    private MovementsGateWay movementsGateWay;

    public FindMovementsUsecases(MovementsGateWay movementsGateWay) {
        this.movementsGateWay = movementsGateWay;
    }

    public Movements execute(Long id) {
        return movementsGateWay.getById(id);
    }

    public List<Movements> execute() {
        return movementsGateWay.getAll();
    }

    public List<Movements> execute(Coins coins) {
        return movementsGateWay.getByCoins(coins);
    }

    public List<Movements> execute(TypeCoins typeCoins) {
        return movementsGateWay.getByTypeCoins(typeCoins);
    }

}
