package com.site.dev.core.applications.usecases.movements;

import com.site.dev.core.applications.gateway.MovementsGateWay;

public class DeleteMovementsUsecases {
    private MovementsGateWay movementsGateWay;
    private ValidadeMovements validadeMovements;

    public DeleteMovementsUsecases(MovementsGateWay movementsGateWay) {
        this.movementsGateWay = movementsGateWay;
        this.validadeMovements = new ValidadeMovements();
    }

    public void execute(Long id) {
        validadeMovements.verifyMovementsExists(id, movementsGateWay);
        movementsGateWay.delete(id);
    }

    

    
}
