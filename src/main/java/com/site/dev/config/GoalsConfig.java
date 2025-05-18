package com.site.dev.config;

import com.site.dev.core.applications.gateway.GoalsGateWay;

import com.site.dev.core.applications.usecases.goals.CreateGoalsUsecases;

import com.site.dev.core.applications.usecases.goals.DeleteGoalsUsecases;
import com.site.dev.core.applications.usecases.goals.FindGoalsUsecases;
import com.site.dev.core.applications.usecases.goals.UpdateGoalsUsecases;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GoalsConfig {

   
    
    
    @Bean
    CreateGoalsUsecases createGoalsUsecases(GoalsGateWay goalsGateWay) {
        return new CreateGoalsUsecases(goalsGateWay);
    }

    @Bean
    FindGoalsUsecases findGoalsUsecases(GoalsGateWay goalsGateWay) {
        return new FindGoalsUsecases(goalsGateWay);
    }
    @Bean
    DeleteGoalsUsecases deleteGoalsUsecases(GoalsGateWay goalsGateWay) {
        return new DeleteGoalsUsecases(goalsGateWay);
    }
    @Bean
    UpdateGoalsUsecases updateGoalsUsecases(GoalsGateWay goalsGateWay) {
        return new UpdateGoalsUsecases(goalsGateWay);
    }

    
}
