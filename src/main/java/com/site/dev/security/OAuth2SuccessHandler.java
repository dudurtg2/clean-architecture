package com.site.dev.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.site.dev.adapter.mappers.UserMapper;
import com.site.dev.core.applications.usecases.users.CreateUsersUsecases;
import com.site.dev.core.applications.usecases.users.FindUsersUsecases;
import com.site.dev.core.domain.entity.Users;
import com.site.dev.core.domain.enums.UserRole;
import com.site.dev.security.dto.TokensDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final JwtTokenProvider jwtProvider;
    private FindUsersUsecases findUsersUsecases;
    private CreateUsersUsecases createUserUsecases;
    private UserMapper userMapper;
    private final ObjectMapper objectMapper;
    public OAuth2SuccessHandler(ObjectMapper objectMapper, JwtTokenProvider jwtProvider,CreateUsersUsecases createUserUsecases, FindUsersUsecases findUsersUsecases, UserMapper userMapper) {
        this.jwtProvider = jwtProvider;
        this.findUsersUsecases = findUsersUsecases;
        this.userMapper = userMapper;
        this.createUserUsecases = createUserUsecases;
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res,
                                        Authentication auth) throws IOException {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) auth;

        OAuth2User oauthUser = oauthToken.getPrincipal();

        String email = oauthUser.getAttribute("email");
        String nome = oauthUser.getAttribute("given_name");
        if (findUsersUsecases.execute(email) == null) {

            createUserUsecases.execute(Users.builder()
                    .email(email)
                    .name(nome)
                    .role(UserRole.NORMAL)
                    .password(nome.replaceAll("\\s", "") + "@Senai")
                    .build());
        }
        TokensDTO tokens = jwtProvider.generateTokens(
                userMapper.toUserEntity(findUsersUsecases.execute(email))
        );

        String json = objectMapper.writeValueAsString(tokens);

        res.setStatus(HttpServletResponse.SC_OK);
        res.setContentType("application/json");
        res.getWriter().write(json);
    }
}
