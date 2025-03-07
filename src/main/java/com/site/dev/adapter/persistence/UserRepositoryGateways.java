package com.site.dev.adapter.persistence;

import java.util.List;

import com.site.dev.adapter.mappers.UserMapper;
import com.site.dev.adapter.repository.UserRepository;
import com.site.dev.core.applications.gateway.UserGateWay;
import com.site.dev.core.domain.entity.User;

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
    @Override
    public List<User> getAllUsers() {
        return userMapper.toResponse(userRepository.findAll());
    }
    @Override
    public User getUserById(Long id) {
        return userMapper.toUser(userRepository.findById(id).get());
    }  
}