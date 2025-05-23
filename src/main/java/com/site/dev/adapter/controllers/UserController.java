package com.site.dev.adapter.controllers;

import com.site.dev.adapter.mappers.UserMapper;
import com.site.dev.adapter.models.ExceptionBody;
import com.site.dev.adapter.models.UsersEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.site.dev.adapter.controllers.dto.users.UsersRequest;
import com.site.dev.adapter.controllers.dto.users.UsersResponse;
import com.site.dev.adapter.mappers.UserDTOMapper;
import com.site.dev.core.applications.usecases.users.CreateUsersUsecases;
import com.site.dev.core.applications.usecases.users.DeleteUsersUsecases;
import com.site.dev.core.applications.usecases.users.FindUsersUsecases;
import com.site.dev.core.applications.usecases.users.UpdateUsersUsecases;
import com.site.dev.services.CollectEmailForTokenService;
import com.site.dev.core.domain.entity.Users;
import com.site.dev.security.dto.AccessTokenResponseDTO;
import com.site.dev.security.dto.AuthorizationDTO;
import com.site.dev.security.dto.LoginResponseDTO;
import com.site.dev.security.dto.RefreshTokenDTO;
import com.site.dev.services.TokenService;

import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final CreateUsersUsecases createUserUsecases;
    private final FindUsersUsecases findUserUsecases;
    private final UpdateUsersUsecases updateUsersUsecases;
    private final DeleteUsersUsecases deleteUsersUsecases;

    private final UserDTOMapper userDTOMapper;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;

    private TokenService tokenService;
    private CollectEmailForTokenService collectEmailForTokenService;

    @Autowired
    public UserController(UpdateUsersUsecases updateUsersUsecases, UserMapper userMapper,
            CreateUsersUsecases createUserUsecases, UserDTOMapper userDTOMapper, FindUsersUsecases findUserUsecases,
            TokenService tokenService, AuthenticationManager authenticationManager,
            DeleteUsersUsecases deleteUsersUsecases, CollectEmailForTokenService collectEmailForTokenService) {
        this.createUserUsecases = createUserUsecases;
        this.userMapper = userMapper;
        this.userDTOMapper = userDTOMapper;
        this.findUserUsecases = findUserUsecases;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.updateUsersUsecases = updateUsersUsecases;
        this.deleteUsersUsecases = deleteUsersUsecases;
        this.collectEmailForTokenService = collectEmailForTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthorizationDTO data) {
        try {
            var usernamePass = new UsernamePasswordAuthenticationToken(
                    data.login().toLowerCase(),
                    data.senha());
            var auth = this.authenticationManager.authenticate(usernamePass);
            var user = (UsersEntity) auth.getPrincipal();
            var tokens = tokenService.generateTokens(user);
            var response = new LoginResponseDTO(findUserUsecases.execute(data.login().toLowerCase()), tokens);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(401).body("Credenciais inválidas ou autenticação falhou.");
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenDTO refreshTokenRequest) {

        String login = tokenService.validateRefreshToken(refreshTokenRequest.refreshToken());
        if (login == null) {
            return ResponseEntity.status(401).body("Refresh Token inválido ou expirado.");
        }

        Users user = findUserUsecases.execute(login);

        return ResponseEntity
                .ok(new AccessTokenResponseDTO(tokenService.generateAccessToken(userMapper.toUserEntity(user))));
    }

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody UsersRequest request) {
        try {

            Users user = userDTOMapper.toUser(request);
            Users createdUser = createUserUsecases.execute(user);
            UsersResponse response = userDTOMapper.toResponse(createdUser);
            return new ResponseEntity<UsersResponse>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            ExceptionBody body = new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<ExceptionBody>(body, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find/{uuid}")
    ResponseEntity<?> find(@PathVariable String uuid) {
        try {
            Users user = findUserUsecases.execute(uuid);
            UsersResponse response = userDTOMapper.toResponse(user);
            return new ResponseEntity<UsersResponse>(response, HttpStatus.OK);
        } catch (Exception e) {
            ExceptionBody body = new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<ExceptionBody>(body, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    ResponseEntity<?> update(@RequestBody UsersRequest request, 
            HttpServletRequest servletRequest) {
        try {
            Users user = userDTOMapper.toUser(request);
            Users updatedUser = updateUsersUsecases.execute(collectEmailForTokenService.execute(servletRequest), user);
            UsersResponse response = userDTOMapper.toResponse(updatedUser);
            return new ResponseEntity<UsersResponse>(response, HttpStatus.OK);
        } catch (Exception e) {
            ExceptionBody body = new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<ExceptionBody>(body, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    ResponseEntity<?> delete(HttpServletRequest servletRequest) {
        try {
            deleteUsersUsecases.execute(collectEmailForTokenService.execute(servletRequest));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            ExceptionBody body = new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<ExceptionBody>(body, HttpStatus.BAD_REQUEST);
        }
    }

    
}
