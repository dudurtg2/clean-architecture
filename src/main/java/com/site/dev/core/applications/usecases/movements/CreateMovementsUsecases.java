package com.site.dev.core.applications.usecases.movements;

import com.site.dev.core.applications.gateway.MovementsGateWay;
import com.site.dev.core.domain.entity.Movements;
import com.site.dev.core.domain.exception.IncorretBoryUserException;
public class CreateMovementsUsecases {
    private MovementsGateWay movementsGateWay;

    public CreateMovementsUsecases(MovementsGateWay movementsGateWay) {
        this.movementsGateWay = movementsGateWay;
    }

    public Movements execute(Movements movements) {
        validate(movements);
        verifyMovementsIsPriceIsNotSet(movements);
        return movementsGateWay.create(movements);
    }

    private void verifyMovementsIsPriceIsNotSet(Movements movements) {
        if(movements.getPrice() == null) movements.setPrice(1f);
    }

    private void validate(Movements movements) {
        if (movements.getCoins() == null 
        || movements.getTypeCoins() == null 
        || movements.getValue() == null) throw new IncorretBoryUserException();

    } 
}
