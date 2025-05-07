package com.site.dev.core.applications.usecases.movements;

import com.site.dev.core.applications.gateway.MovementsGateWay;
import com.site.dev.core.domain.exception.NotExistsEntityException;

public class DeleteMovementsUsecases {
    private MovementsGateWay movementsGateWay;

    public DeleteMovementsUsecases(MovementsGateWay movementsGateWay) {
        this.movementsGateWay = movementsGateWay;
    }

    public void execute(Long id) {
        if(movementsGateWay.getById(id) == null) throw new NotExistsEntityException("Movements");
        movementsGateWay.delete(id);
    }

    

    
}
