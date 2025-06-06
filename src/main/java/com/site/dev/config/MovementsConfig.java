package com.site.dev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.site.dev.core.applications.gateway.MovementsGateWay;
import com.site.dev.core.applications.usecases.movements.CreateMovementsUsecases;
import com.site.dev.core.applications.usecases.movements.DeleteMovementsUsecases;
import com.site.dev.core.applications.usecases.movements.FindMovementsUsecases;
import com.site.dev.core.applications.usecases.movements.UpdateMovementsUsecases;



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

    @Bean
    DeleteMovementsUsecases deleteMovementsUsecases(MovementsGateWay movementsGateWay) {
        return new DeleteMovementsUsecases(movementsGateWay);
    }

    @Bean
    UpdateMovementsUsecases updateMovementsUsecases(MovementsGateWay movementsGateWay) {
        return new UpdateMovementsUsecases(movementsGateWay);
    }

    
}
