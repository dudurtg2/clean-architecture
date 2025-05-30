package com.site.dev.adapter.persistence;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.site.dev.adapter.mappers.UserMapper;
import com.site.dev.adapter.repository.UserRepository;
import com.site.dev.core.applications.gateway.UsersGateWay;
import com.site.dev.core.domain.entity.Users;
@Component
public class UserRepositoryGateways implements UsersGateWay {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    
    @Autowired
    public UserRepositoryGateways(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    @Override
    public Users createUser(Users user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        System.out.println(user);
        return userMapper.toUser(userRepository.save(userMapper.toUserEntity(user)));
    }
    @Override
    public List<Users> getAllUsers() {
        return userMapper.toResponse(userRepository.findAll());
    }
    @Override
    public Users getUserByUUID(UUID uuid) {
        return userMapper.toUser(userRepository.findByUuid(uuid));
    }
    @Override
    public Users getUserByEmail(String email) {

        return userMapper.toUser(
                userRepository.findByEmail(email));
    }  
    @Override
    public Users update(Users user) {
        return userMapper.toUser(userRepository.save(userMapper.toUserEntity(user)));
    }
    @Override
    public void delete(UUID uuid) {
        userRepository.delete(userRepository.findByUuid(uuid));
    }

    @Override
    public String cryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}