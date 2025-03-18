package com.site.dev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.site.dev.core.applications.gateway.MovementsGateWay;
import com.site.dev.core.applications.usecases.movements.CreateMovementsUsecases;
import com.site.dev.core.applications.usecases.movements.FindMovementsUsecases;

@Configuration
public class MovementsConfig {
    
    
    @Bean
    CreateMovementsUsecases createMovementsUsecases(MovementsGateWay movementsGateWay) {
        return new CreateMovementsUsecases(movementsGateWay);
    }

    @Bean
    FindMovementsUsecases findMovementsUsecases(MovementsGateWay movementsGateWay) {
        return new FindMovementsUsecases(movementsGateWay);
    }

    
}
