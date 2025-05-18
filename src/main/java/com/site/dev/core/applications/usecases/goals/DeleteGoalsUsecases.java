package com.site.dev.core.applications.usecases.goals;

import com.site.dev.core.applications.gateway.GoalsGateWay;
import com.site.dev.core.applications.gateway.MovementsGateWay;
import com.site.dev.core.domain.exception.NotExistsEntityException;

import java.util.UUID;

public class DeleteGoalsUsecases {
    private GoalsGateWay goalsGateWay;

    public DeleteGoalsUsecases(GoalsGateWay goalsGateWay) {
        this.goalsGateWay = goalsGateWay;
    }

    public void execute(UUID uuid) {
        if(goalsGateWay.getByUUID(uuid) == null) throw new NotExistsEntityException("Goals");
        goalsGateWay.delete(uuid);
    }

    

    
}
