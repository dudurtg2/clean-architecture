package com.site.dev.infrastructure.gateways;

import com.site.dev.core.domain.entity.User;
import com.site.dev.core.gateways.UserGateWay;
import com.site.dev.infrastructure.mappers.UserMapper;
import com.site.dev.infrastructure.persistence.repository.UserRepository;

public class UserRepositoryGateways implements UserGateWay {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserRepositoryGateways(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    @Override
    public User createUser(User user) {
        return userMapper.toUser(userRepository.save(userMapper.toUserEntity(user)));
    }  
}