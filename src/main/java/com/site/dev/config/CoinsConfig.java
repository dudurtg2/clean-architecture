package com.site.dev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.site.dev.core.applications.gateway.CoinsGateWay;
import com.site.dev.core.applications.gateway.MovementsGateWay;

import com.site.dev.core.applications.usecases.coins.CreateCoinsUsecases;
import com.site.dev.core.applications.usecases.coins.DeleteCoinsUsecases;
import com.site.dev.core.applications.usecases.coins.FindCoinsUsecases;
import com.site.dev.core.applications.usecases.coins.UpdateCoinsUsecases;

@Configuration
public class CoinsConfig {
   
    
    @Bean
    CreateCoinsUsecases createCoinsUsecases(CoinsGateWay coinsGateWay) {
        return new CreateCoinsUsecases(coinsGateWay);
    }

    @Bean
    FindCoinsUsecases findCoinsUsecases(CoinsGateWay coinsGateWay) {
        return new FindCoinsUsecases(coinsGateWay);
    }

    @Bean
    DeleteCoinsUsecases deleteCoinsUsecases(CoinsGateWay coinsGateWay, 
            MovementsGateWay movementsGateWay) {
        return new DeleteCoinsUsecases(coinsGateWay, movementsGateWay);
    }

    @Bean
    UpdateCoinsUsecases updateCoinsUsecases(CoinsGateWay coinsGateWay) {
        return new UpdateCoinsUsecases(coinsGateWay);
    }

    
}
    
    