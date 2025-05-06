package com.site.dev.config;

import org.hibernate.sql.Delete;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.site.dev.core.applications.gateway.CoinsGateWay;
import com.site.dev.core.applications.usecases.coins.CreateCoinsUsecases;
import com.site.dev.core.applications.usecases.coins.DeleteCoinsUsecases;
import com.site.dev.core.applications.usecases.coins.FindCoinsUsecases;
import com.site.dev.core.applications.usecases.coins.UpdateCoinsUsecases;
import com.site.dev.core.applications.usecases.coins.ValidadeCoins;
import com.site.dev.core.applications.usecases.users.UpdateUsersUsecases;

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
    DeleteCoinsUsecases deleteCoinsUsecases(CoinsGateWay coinsGateWay) {
        return new DeleteCoinsUsecases(coinsGateWay);
    }

    @Bean
    UpdateCoinsUsecases updateCoinsUsecases(CoinsGateWay coinsGateWay) {
        return new UpdateCoinsUsecases(coinsGateWay);
    }

    
}
    
    