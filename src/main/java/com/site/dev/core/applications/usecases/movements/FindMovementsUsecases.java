package com.site.dev.core.applications.usecases.movements;

import java.util.List;
import java.util.UUID;

import com.site.dev.core.applications.gateway.MovementsGateWay;
import com.site.dev.core.domain.entity.Coins;
import com.site.dev.core.domain.entity.Movements;
import com.site.dev.core.domain.entity.Users;
import com.site.dev.core.domain.enums.TipoDespesa;
import com.site.dev.core.domain.enums.TypeCoinSearch;
import com.site.dev.core.domain.enums.TypeCoins;
public class FindMovementsUsecases {
    private MovementsGateWay movementsGateWay;

    public FindMovementsUsecases(MovementsGateWay movementsGateWay) {
        this.movementsGateWay = movementsGateWay;
    }

    public Movements execute(UUID uuid) {
        return movementsGateWay.getByUUID(uuid);
    }

    public List<Movements> execute() {
        return movementsGateWay.getAll();
    }

    public List<Movements> execute(Coins coins) {
        return movementsGateWay.getByCoins(coins);
    }

    public List<Movements> execute(TipoDespesa tipoDespesa) {
        return movementsGateWay.getByTipoDespesa(tipoDespesa);
    }

    public List<Movements> execute(TypeCoinSearch coins, Users users) {
       switch (coins) {
        case CRYPTO:
            return movementsGateWay.getAll().stream().filter(movements -> movements.getCoins().getUser().equals(users))
                    .filter(movements -> movements.getCoins().getIsCrypto() == true).toList();
        case GOAL:
            return movementsGateWay.getAll().stream().filter(movements -> movements.getCoins().getUser().equals(users))
                    .filter(movements -> movements.getCoins().getIsGoal() == true).toList();
        case ACTIVE:
            return movementsGateWay.getAll().stream().filter(movements -> movements.getCoins().getUser().equals(users))
                    .filter(movements -> movements.getCoins().getIsActive() == true).toList();
        default:
            return null;
       }
    }

}
