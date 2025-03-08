package com.site.dev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.site.dev.adapter.mappers.UserDTOMapper;
import com.site.dev.adapter.mappers.UserMapper;
import com.site.dev.adapter.persistence.UserRepositoryGateways;
import com.site.dev.adapter.repository.UserRepository;
import com.site.dev.core.applications.gateway.UserGateWay;
import com.site.dev.core.applications.usecases.CreateUserUsecases;
import com.site.dev.core.applications.usecases.FindUserUsecases;

@Configuration
public class UserConfig {
    @Bean
    UserGateWay userRepositoryGateways(UserMapper userMapper, UserRepository userRepository) {
        return new UserRepositoryGateways(userRepository, userMapper);
    }

    @Bean
    CreateUserUsecases createUserUsecases(UserGateWay userGateWay) {
        return new CreateUserUsecases(userGateWay);
    }

    @Bean
    FindUserUsecases findUserUsecases(UserGateWay userGateWay) {
        return new FindUserUsecases(userGateWay);
    }

    @Bean
    UserMapper userMapper() {
        return new UserMapper();
    }
    
    @Bean
    UserDTOMapper userDTOMapper() {
        return new UserDTOMapper();
    }
}
