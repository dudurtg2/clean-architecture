package com.site.dev.core.applications.usecases.movements;

import com.site.dev.core.applications.gateway.MovementsGateWay;
import com.site.dev.core.domain.entity.Movements;

public class CreateMovementsUsecases {
    private MovementsGateWay movementsGateWay;

    public CreateMovementsUsecases(MovementsGateWay movementsGateWay) {
        this.movementsGateWay = movementsGateWay;
    }

    public Movements execute(Movements movements) {
        return movementsGateWay.create(movements.correct());
    }

    
}
