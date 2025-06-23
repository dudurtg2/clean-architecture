package com.site.dev.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.site.dev.adapter.mappers.UserMapper;
import com.site.dev.core.applications.usecases.users.LoginUsersUsecases;
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

    private LoginUsersUsecases loginUsersUsecases;
    private UserMapper userMapper;
    private final ObjectMapper objectMapper;

    public OAuth2SuccessHandler(ObjectMapper objectMapper, JwtTokenProvider jwtProvider, LoginUsersUsecases loginUsersUsecases, UserMapper userMapper) {
        this.jwtProvider = jwtProvider;
        this.userMapper = userMapper;
        this.loginUsersUsecases = loginUsersUsecases;
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res,
                                        Authentication auth) throws IOException {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) auth;

        OAuth2User oauthUser = oauthToken.getPrincipal();

        String email = oauthUser.getAttribute("email");
        String sub = oauthUser.getAttribute("sub");
        String nome = oauthUser.getAttribute("given_name");

        TokensDTO tokens = jwtProvider.generateTokens(
                userMapper.toUserEntity(loginUsersUsecases.google(sub, email, nome))
        );

        String json = objectMapper.writeValueAsString(tokens);

        res.setStatus(HttpServletResponse.SC_OK);
        res.setContentType("application/json");
        res.getWriter().write(json);
    }
}
