package com.site.dev.core.applications.usecases.movements;

import com.site.dev.core.applications.gateway.MovementsGateWay;
import com.site.dev.core.domain.exception.NotExistsEntityException;

import java.util.UUID;

public class DeleteMovementsUsecases {
    private MovementsGateWay movementsGateWay;

    public DeleteMovementsUsecases(MovementsGateWay movementsGateWay) {
        this.movementsGateWay = movementsGateWay;
    }

    public void execute(UUID uuid) {
        if(movementsGateWay.geyByUUID(uuid) == null) throw new NotExistsEntityException("Movements");
        movementsGateWay.delete(uuid);
    }

    

    
}
