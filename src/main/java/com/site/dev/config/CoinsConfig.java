package com.site.dev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.site.dev.core.applications.gateway.CoinsGateWay;
import com.site.dev.core.applications.usecases.coins.CreateCoinsUsecases;
import com.site.dev.core.applications.usecases.coins.FindCoinsUsecases;

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

    
}
