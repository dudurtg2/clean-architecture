package com.site.dev.adapter.controllers;

import com.site.dev.adapter.controllers.dto.users.RoleUsers;
import com.site.dev.adapter.controllers.dto.users.UpdatesUsers;
import com.site.dev.adapter.mappers.UserMapper;
import com.site.dev.adapter.models.ExceptionBody;
import com.site.dev.adapter.models.UsersEntity;

import com.site.dev.core.domain.enums.UserRole;
import com.site.dev.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
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
    
    private JwtTokenProvider jwtTokenProvider;
    private CollectEmailForTokenService collectEmailForTokenService;

    @Autowired
    public UserController(UpdateUsersUsecases updateUsersUsecases, UserMapper userMapper,
            JwtTokenProvider jwtTokenProvider,
            CreateUsersUsecases createUserUsecases, UserDTOMapper userDTOMapper, FindUsersUsecases findUserUsecases,
            AuthenticationManager authenticationManager,
            DeleteUsersUsecases deleteUsersUsecases, CollectEmailForTokenService collectEmailForTokenService) {
        this.createUserUsecases = createUserUsecases;
        this.userMapper = userMapper;
        this.userDTOMapper = userDTOMapper;
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
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(data.login(), data.senha())
            );

            Users user = findUserUsecases.execute(data.login());
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
    public void loginGoogle(@AuthenticationPrincipal OidcUser user) {

    }

    @GetMapping("/online")
    public ResponseEntity<?> online() {
        return ResponseEntity.ok("Usuário online");
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
