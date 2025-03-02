package com.site.dev.infrastructure.mappers;

import com.site.dev.core.domain.entity.User;
import com.site.dev.infrastructure.persistence.entity.UserEntity;

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
}
