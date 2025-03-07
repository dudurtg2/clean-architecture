package com.site.dev.adapter.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.site.dev.adapter.controllers.DTO.response.CreateUserResponse;
import com.site.dev.adapter.controllers.DTO.resquest.CreateUserRequest;
import com.site.dev.adapter.entity.ExceptionBody;
import com.site.dev.adapter.mappers.UserDTOMapper;
import com.site.dev.core.applications.usecases.CreateUserUsecases;
import com.site.dev.core.applications.usecases.FindUserUsecases;
import com.site.dev.core.domain.entity.User;


@RestController
@RequestMapping("/api/user")
public class UserController {
    private CreateUserUsecases createUserUsecases;
    private FindUserUsecases findUserUsecases;
    private UserDTOMapper userMapper;

    
    public UserController(CreateUserUsecases createUserUsecases, UserDTOMapper userMapper, FindUserUsecases findUserUsecases) {
        this.createUserUsecases = createUserUsecases;
        this.userMapper = userMapper;
        this.findUserUsecases = findUserUsecases;
    }

    @PostMapping("/create")
    ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        try {
            User user = userMapper.toUser(request);
            User createdUser = createUserUsecases.execute(user);
            CreateUserResponse response = userMapper.toResponse(createdUser);
            return new ResponseEntity<CreateUserResponse>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            ExceptionBody body = new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<ExceptionBody>(body, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find/{id}")
    ResponseEntity<?> findUser(@PathVariable Long id) {
        try {
            User user = findUserUsecases.execute(id);
            CreateUserResponse response = userMapper.toResponse(user);
            return new ResponseEntity<CreateUserResponse>(response, HttpStatus.OK);
        } catch (Exception e) {
            ExceptionBody body = new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<ExceptionBody>(body, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    ResponseEntity<?> findAll() {
        try {
            List<User> users = findUserUsecases.execute();
            List<CreateUserResponse> response = userMapper.toResponse(users);
            return new ResponseEntity<List<CreateUserResponse>>(response, HttpStatus.OK);
        } catch (Exception e) {
            ExceptionBody body = new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<ExceptionBody>(body, HttpStatus.BAD_REQUEST);
        }
    }


}
