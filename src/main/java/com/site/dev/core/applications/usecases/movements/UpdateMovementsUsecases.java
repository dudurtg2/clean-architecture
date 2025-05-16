package com.site.dev.core.applications.usecases.movements;

import com.site.dev.core.applications.gateway.MovementsGateWay;
import com.site.dev.core.domain.entity.Movements;
import com.site.dev.core.domain.exception.NotExistsEntityException;

import java.util.UUID;

public class UpdateMovementsUsecases {
    private MovementsGateWay movementsGateWay;

    public UpdateMovementsUsecases(MovementsGateWay movementsGateWay) {
        this.movementsGateWay = movementsGateWay;
    }

    public Movements execute(UUID uuid, Movements movements) {
        if(movementsGateWay.geyByUUID(uuid) == null) throw new NotExistsEntityException("Movements");
        validateNewBory(movements);

        return movementsGateWay.update(movements);
    }
    
    public void validateNewBory(Movements movements) {
        Movements movementsInBD = movementsGateWay.geyByUUID(movements.getUuid());
        movements.setCoins(movements.getCoins() == null ? movementsInBD.getCoins() : movements.getCoins());
        movements.setTypeCoins(movements.getTypeCoins() == null ? movementsInBD.getTypeCoins() : movements.getTypeCoins());
        movements.setValue(movements.getValue() == null ? movementsInBD.getValue() : movements.getValue());
        movements.setDate(movements.getDate() == null ? movementsInBD.getDate() : movements.getDate());
        movements.setPrice(movements.getPrice() == null ? movementsInBD.getPrice() : movements.getPrice());
    }  
}
