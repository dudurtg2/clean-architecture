package com.site.dev.config;

import com.site.dev.core.applications.usecases.users.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.site.dev.core.applications.gateway.UsersGateWay;


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
        return new UpdateUsersUsecases(userGateWay );
    }
    
    @Bean
    DeleteUsersUsecases deleteUserUsecases(UsersGateWay userGateWay) {
        return new DeleteUsersUsecases(userGateWay);
    }

    @Bean
    LoginUsersUsecases loginUsersUsecases(UsersGateWay userGateWay) {
        return new LoginUsersUsecases(userGateWay);
    }
}
