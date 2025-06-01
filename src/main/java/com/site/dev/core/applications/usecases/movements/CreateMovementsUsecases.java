package com.site.dev.core.applications.usecases.movements;

import com.site.dev.core.applications.gateway.MovementsGateWay;
import com.site.dev.core.domain.entity.Movements;
import com.site.dev.core.domain.exception.NoDuplicateEntityException;

import java.time.LocalDateTime;

public class CreateMovementsUsecases {
    private MovementsGateWay movementsGateWay;

    public CreateMovementsUsecases(MovementsGateWay movementsGateWay) {
        this.movementsGateWay = movementsGateWay;
    }

    public Movements execute(Movements movements) {

        if (movementsGateWay.getByUUID(movements.getUuid()) != null) {
            throw new NoDuplicateEntityException("Movements");
        }
        movements.setDate(LocalDateTime.now());
        return movementsGateWay.create(movements.correct());
    }

    
}
