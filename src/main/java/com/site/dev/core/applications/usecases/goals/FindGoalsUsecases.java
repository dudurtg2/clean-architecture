package com.site.dev.core.applications.usecases.goals;

import com.site.dev.core.applications.gateway.GoalsGateWay;
import com.site.dev.core.applications.gateway.MovementsGateWay;
import com.site.dev.core.domain.entity.Coins;
import com.site.dev.core.domain.entity.Goals;
import com.site.dev.core.domain.entity.Movements;
import com.site.dev.core.domain.enums.TypeCoins;

import java.util.List;
import java.util.UUID;
public class FindGoalsUsecases {
    private GoalsGateWay goalsGateWay;

    public FindGoalsUsecases(GoalsGateWay goalsGateWay) {
        this.goalsGateWay = goalsGateWay;
    }

    public Goals execute(UUID uuid) {
        return goalsGateWay.getByUUID(uuid);
    }

    public List<Goals> execute() {
        return goalsGateWay.getAll();
    }

    public List<Goals> execute(Coins coins) {
        return goalsGateWay.getByCoins(coins);
    }


}
