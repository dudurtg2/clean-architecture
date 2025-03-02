package com.site.dev.infrastructure.persistence.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.site.dev.core.domain.entity.User;
import com.site.dev.core.domain.usecases.CreateUserUsecases;
import com.site.dev.infrastructure.mappers.UserDTOMapper;
import com.site.dev.infrastructure.persistence.controllers.DTO.response.CreateUserResponse;
import com.site.dev.infrastructure.persistence.controllers.DTO.resquest.CreateUserRequest;
import com.site.dev.infrastructure.persistence.controllers.exception.ExceptionBody;


@RestController
@RequestMapping("/api/user")
public class UserController {
    private CreateUserUsecases createUserUsecases;
    private UserDTOMapper userMapper;

    public UserController(CreateUserUsecases createUserUsecases, UserDTOMapper userMapper) {
        this.createUserUsecases = createUserUsecases;
        this.userMapper = userMapper;
    }

    @PostMapping("/create")
    ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        try {
            User user = userMapper.toUser(request);
            User createdUser = createUserUsecases.execute(user);
            CreateUserResponse response = userMapper.toResponse(createdUser);
            return new ResponseEntity<CreateUserResponse>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            ExceptionBody body = new ExceptionBody(e.getMessage(), e.getCause().hashCode());
            return new ResponseEntity<ExceptionBody>(body, HttpStatus.BAD_REQUEST);
        }
    }

}
