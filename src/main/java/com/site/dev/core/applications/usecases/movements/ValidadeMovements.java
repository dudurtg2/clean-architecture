package com.site.dev.core.applications.usecases.movements;

import com.site.dev.core.applications.gateway.MovementsGateWay;
import com.site.dev.core.domain.entity.Movements;
import com.site.dev.core.domain.exception.IncorretBoryUserException;
import com.site.dev.core.domain.exception.NotExistsEntityException;

public class ValidadeMovements {
    
    public void validateBory(Movements movements) {
        if (movements.getCoins() == null 
        || movements.getTypeCoins() == null 
        || movements.getValue() == null) throw new IncorretBoryUserException();

    } 

    public void verifyMovementsIsPriceIsNotSet(Movements movements) {
        if(movements.getPrice() == null) movements.setPrice(1f);
    }

    public void validateNewBory(Movements movements, MovementsGateWay movementsGateWay) {
        Movements movementsInBD = movementsGateWay.getById(movements.getId());

        movements.setCoins(movements.getCoins() == null ? movementsInBD.getCoins() : movements.getCoins());
        movements.setTypeCoins(movements.getTypeCoins() == null ? movementsInBD.getTypeCoins() : movements.getTypeCoins());
        movements.setValue(movements.getValue() == null ? movementsInBD.getValue() : movements.getValue());
        movements.setDate(movements.getDate() == null ? movementsInBD.getDate() : movements.getDate());
        movements.setPrice(movements.getPrice() == null ? movementsInBD.getPrice() : movements.getPrice());

    } 

    public void verifyMovementsExists(Long id, MovementsGateWay movementsGateWay) {
        if(movementsGateWay.getById(id) == null) throw new NotExistsEntityException("Movements");
    }
}
