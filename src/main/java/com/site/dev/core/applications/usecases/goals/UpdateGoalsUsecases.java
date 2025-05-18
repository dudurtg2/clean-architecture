package com.site.dev.core.applications.usecases.goals;

import com.site.dev.core.applications.gateway.GoalsGateWay;
import com.site.dev.core.applications.gateway.MovementsGateWay;
import com.site.dev.core.domain.entity.Goals;
import com.site.dev.core.domain.entity.Movements;
import com.site.dev.core.domain.exception.NotExistsEntityException;

import java.util.UUID;

public class UpdateGoalsUsecases {
    private GoalsGateWay goalsGateWay;

    public UpdateGoalsUsecases(GoalsGateWay goalsGateWay) {
        this.goalsGateWay = goalsGateWay;
    }

    public Goals execute(UUID uuid, Goals goals) {
        if(goalsGateWay.getByUUID(uuid) == null) throw new NotExistsEntityException("Goals");
        validateNewBory(goals);

        return goalsGateWay.update(goals.correct());
    }
    
    public void validateNewBory(Goals goals) {
        Goals goalsInBD = goalsGateWay.getByUUID(goals.getUuid());
        goals.setCoins(goals.getCoins() == null ? goalsInBD.getCoins() : goals.getCoins());
        goals.setData(goals.getData() == null ? goalsInBD.getData() : goals.getData());
        goals.setDescription( goals.getDescription() == null ? goalsInBD.getDescription() : goals.getDescription());
        goals.setGoal(goals.getGoal() < 0 ? goalsInBD.getGoal() : goals.getGoal());
        goals.setUser(goalsInBD.getUser());
       

    }  
}
