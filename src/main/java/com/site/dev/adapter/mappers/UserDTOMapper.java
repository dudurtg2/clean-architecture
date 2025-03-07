package com.site.dev.adapter.mappers;

import java.util.List;

import com.site.dev.adapter.controllers.DTO.response.CreateUserResponse;
import com.site.dev.adapter.controllers.DTO.resquest.CreateUserRequest;
import com.site.dev.core.domain.entity.User;

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

    public List<CreateUserResponse> toResponse(List<User> users) {
        return users.stream()
                .map(this::toResponse)
                .toList();
    }
}
