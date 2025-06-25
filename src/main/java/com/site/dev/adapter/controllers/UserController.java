package com.site.dev.adapter.controllers;

import com.site.dev.adapter.controllers.dto.users.*;
import com.site.dev.adapter.mappers.UserMapper;
import com.site.dev.adapter.models.ExceptionBody;

import com.site.dev.core.applications.usecases.users.*;
import com.site.dev.core.domain.enums.UserRole;
import com.site.dev.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.site.dev.adapter.mappers.UserDTOMapper;
import com.site.dev.services.CollectEmailForTokenService;
import com.site.dev.core.domain.entity.Users;
import com.site.dev.security.dto.AccessTokenResponseDTO;
import com.site.dev.security.dto.AuthorizationDTO;
import com.site.dev.security.dto.RefreshTokenDTO;

import jakarta.servlet.http.HttpServletRequest;

import java.net.URI;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final CreateUsersUsecases createUserUsecases;
    private final FindUsersUsecases findUserUsecases;
    private final UpdateUsersUsecases updateUsersUsecases;
    private final DeleteUsersUsecases deleteUsersUsecases;
    private final LoginUsersUsecases loginUsersUsecases;
    private final UserDTOMapper userDTOMapper;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    
    private JwtTokenProvider jwtTokenProvider;
    private CollectEmailForTokenService collectEmailForTokenService;

    @Autowired
    public UserController(UpdateUsersUsecases updateUsersUsecases, UserMapper userMapper,
            JwtTokenProvider jwtTokenProvider,
            CreateUsersUsecases createUserUsecases, UserDTOMapper userDTOMapper, FindUsersUsecases findUserUsecases,
            AuthenticationManager authenticationManager,LoginUsersUsecases loginUsersUsecases,
            DeleteUsersUsecases deleteUsersUsecases, CollectEmailForTokenService collectEmailForTokenService) {
        this.createUserUsecases = createUserUsecases;
        this.userMapper = userMapper;
        this.userDTOMapper = userDTOMapper;
        this.loginUsersUsecases = loginUsersUsecases;
        this.findUserUsecases = findUserUsecases;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.updateUsersUsecases = updateUsersUsecases;
        this.deleteUsersUsecases = deleteUsersUsecases;
        this.collectEmailForTokenService = collectEmailForTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthorizationDTO data) {
        try {
            Users user = findUserUsecases.execute(data.login());

            if (new BCryptPasswordEncoder().matches(data.senha(), user.getPassword())) {
                return ResponseEntity.status(401).body("Credenciais inválidas ou autenticação falhou.");
            }
            return ResponseEntity.ok(
                  jwtTokenProvider.generateTokens(
                          userMapper.toUserEntity(
                                  user
                         )
                    )
            );

        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/login/app/google")
    public ResponseEntity<?> loginGoogle(@RequestBody GoogleUsers data) {
        try {

            Users user = loginUsersUsecases.execute(data.sub(), data.email(), data.name());
            System.out.println("User: " + user);

            return ResponseEntity.ok(
                    jwtTokenProvider.generateTokens(
                            userMapper.toUserEntity(
                                    user
                            )
                    )
            );

        } catch (Exception e) {
            return ResponseEntity.status(401).body("Credenciais inválidas ou autenticação falhou.");
        }
    }

    @GetMapping("/login/google")
    public ResponseEntity<Void> loginGoogleRedirect() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create("/oauth2/authorization/google"))
                .build();
    }


    @GetMapping("/online")
    public ResponseEntity<Boolean> online() {
       return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenDTO refreshTokenRequest) {

        String login = jwtTokenProvider.validateRefreshToken(refreshTokenRequest.refreshToken());
        if (login == null) {
            return ResponseEntity.status(401).body("Refresh Token inválido ou expirado.");
        }

        Users user = findUserUsecases.execute(login);

        return ResponseEntity
                .ok(new AccessTokenResponseDTO(jwtTokenProvider.generateAccessToken(userMapper.toUserEntity(user))));
    }

    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody UsersRequest request) {
        try {

            Users user = userDTOMapper.toUser(request);
            user.setRole(UserRole.NORMAL); // Default role for new users
            Users createdUser = createUserUsecases.execute(user);
            return ResponseEntity.ok(  jwtTokenProvider.generateTokens(
                    userMapper.toUserEntity(
                            createdUser
                    ))
            );
        } catch (Exception e) {
            ExceptionBody body = new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<ExceptionBody>(body, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find")
    ResponseEntity<?> find(HttpServletRequest servletRequest) { //MUDA
        try {
            Users user = findUserUsecases.execute(collectEmailForTokenService.execute(servletRequest));
            UsersResponse response = userDTOMapper.toResponse(user);
            return new ResponseEntity<UsersResponse>(response, HttpStatus.OK);
        } catch (Exception e) {
            ExceptionBody body = new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<ExceptionBody>(body, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/check")
    ResponseEntity<?> check(HttpServletRequest servletRequest) { //MUDA
        try {
            Users user = findUserUsecases.execute(collectEmailForTokenService.execute(servletRequest));
            return new ResponseEntity<RoleUsers>(new RoleUsers(user.getRole()), HttpStatus.OK);
        } catch (Exception e) {
            ExceptionBody body = new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<ExceptionBody>(body, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    ResponseEntity<?> update(@RequestBody UpdatesUsers request,
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

    @PutMapping("/premium")
    ResponseEntity<?> premium(HttpServletRequest servletRequest) {

        try {
            Users user = findUserUsecases.execute(collectEmailForTokenService.execute(servletRequest));
            user.setRole(UserRole.PREMIUM);
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
