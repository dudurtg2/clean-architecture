package com.site.dev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.site.dev.core.applications.gateway.UsersGateWay;
import com.site.dev.core.applications.usecases.users.CreateUsersUsecases;
import com.site.dev.core.applications.usecases.users.FindUsersUsecases;
import com.site.dev.core.applications.usecases.users.UpdateUsersUsecases;

@Configuration
public class UserConfig {
    
    
    @Bean
    CreateUsersUsecases createUserUsecases(UsersGateWay userGateWay) {
        return new CreateUsersUsecases(userGateWay);
    }

    @Bean
    FindUsersUsecases findUserUsecases(UsersGateWay userGateWay) {
        return new FindUsersUsecases(userGateWay);
    }

    @Bean 
    UpdateUsersUsecases updateUserUsecases(UsersGateWay userGateWay) {
        return new UpdateUsersUsecases(userGateWay);
    }
    
}
