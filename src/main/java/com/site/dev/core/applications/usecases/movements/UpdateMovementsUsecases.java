package com.site.dev.core.applications.usecases.movements;

import com.site.dev.core.applications.gateway.MovementsGateWay;
import com.site.dev.core.domain.entity.Movements;

public class UpdateMovementsUsecases {
    private MovementsGateWay movementsGateWay;
    private ValidadeMovements validadeMovements;

    public UpdateMovementsUsecases(MovementsGateWay movementsGateWay) {
        this.movementsGateWay = movementsGateWay;
        this.validadeMovements = new ValidadeMovements();
    }

    public Movements execute(Long id, Movements movements) {
        validadeMovements.verifyMovementsExists(id, movementsGateWay);
        validadeMovements.validateBory(movements);
        return movementsGateWay.update(movements);
    }

    
}
