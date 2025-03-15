package com.site.dev.adapter.controllers;


import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.site.dev.adapter.controllers.DTO.users.CreateUserRequest;
import com.site.dev.adapter.controllers.DTO.users.CreateUserResponse;
import com.site.dev.adapter.entity.ExceptionBody;
import com.site.dev.adapter.mappers.users.UserDTOMapper;
import com.site.dev.core.applications.usecases.users.CreateUsersUsecases;
import com.site.dev.core.applications.usecases.users.FindUsersUsecases;
import com.site.dev.core.domain.entity.Users;


@RestController
@RequestMapping("/api/user")
public class UserController {
    private final CreateUsersUsecases createUserUsecases;
    private final FindUsersUsecases findUserUsecases;
    private final UserDTOMapper userMapper;

    
    public UserController(CreateUsersUsecases createUserUsecases, UserDTOMapper userMapper, FindUsersUsecases findUserUsecases) {
        this.createUserUsecases = createUserUsecases;
        this.userMapper = userMapper;
        this.findUserUsecases = findUserUsecases;
    }

    @PostMapping("/create")
    ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        try {
            Users user = userMapper.toUser(request);
            Users createdUser = createUserUsecases.execute(user);
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
            Users user = findUserUsecases.execute(id);
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
            List<Users> users = findUserUsecases.execute();
            List<CreateUserResponse> response = userMapper.toResponse(users);
            return new ResponseEntity<List<CreateUserResponse>>(response, HttpStatus.OK);
        } catch (Exception e) {
            ExceptionBody body = new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<ExceptionBody>(body, HttpStatus.BAD_REQUEST);
        }
    }


}
