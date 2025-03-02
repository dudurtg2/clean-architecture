package com.site.dev.infrastructure.mappers;

import org.springframework.http.HttpStatusCode;

import com.site.dev.core.domain.entity.User;
import com.site.dev.infrastructure.persistence.controllers.DTO.response.CreateUserResponse;
import com.site.dev.infrastructure.persistence.controllers.DTO.resquest.CreateUserRequest;

public class UserDTOMapper {
    public User toUser(CreateUserRequest createUserRequest) {
        return User.builder()
                .name(createUserRequest.name())
                .email(createUserRequest.email())
                .password(createUserRequest.password())
                .build();
    }

    public CreateUserResponse toResponse(User user) {
        return CreateUserResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
