package com.site.dev.core.applications.usecases.goals;

import com.site.dev.core.applications.gateway.GoalsGateWay;
import com.site.dev.core.applications.gateway.MovementsGateWay;
import com.site.dev.core.domain.entity.Goals;
import com.site.dev.core.domain.entity.Movements;
import com.site.dev.core.domain.exception.NoDuplicateEntityException;

public class CreateGoalsUsecases {
    private GoalsGateWay goalsGateWay;

    public CreateGoalsUsecases(GoalsGateWay goalsGateWay) {
        this.goalsGateWay = goalsGateWay;
    }

    public Goals execute(Goals goals) {
        if (goalsGateWay.getByUUID(goals.getUuid()) != null) {
            throw new NoDuplicateEntityException("Goals");
        }
        return goalsGateWay.create(goals.correct());
    }

    
}
