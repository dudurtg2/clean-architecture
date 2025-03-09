package com.site.dev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.site.dev.core.applications.gateway.UserGateWay;
import com.site.dev.core.applications.usecases.CreateUserUsecases;
import com.site.dev.core.applications.usecases.FindUserUsecases;

@Configuration
public class UserConfig {
    
    
    @Bean
    CreateUserUsecases createUserUsecases(UserGateWay userGateWay) {
        return new CreateUserUsecases(userGateWay);
    }

    @Bean
    FindUserUsecases findUserUsecases(UserGateWay userGateWay) {
        return new FindUserUsecases(userGateWay);
    }

    
}
