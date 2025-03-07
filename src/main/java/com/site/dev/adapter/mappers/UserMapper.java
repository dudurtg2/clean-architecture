package com.site.dev.adapter.mappers;

import java.util.List;

import com.site.dev.adapter.entity.UserEntity;
import com.site.dev.core.domain.entity.User;

public class UserMapper {
    public User toUser(UserEntity userEntity) {
        return User.builder()
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();
    }

    public UserEntity toUserEntity(User user) {
        return UserEntity.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public List<User> toResponse(List<UserEntity> users) {
        return users.stream()
                .map(this::toUser)
                .toList();
    }
}
