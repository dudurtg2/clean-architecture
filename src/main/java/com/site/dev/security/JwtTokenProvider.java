package com.site.dev.security;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;

import com.site.dev.adapter.mappers.UserMapper;
import com.site.dev.core.applications.usecases.users.FindUsersUsecases;
import com.site.dev.core.applications.usecases.users.UpdateUsersUsecases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.site.dev.adapter.models.UsersEntity;
import com.site.dev.security.dto.TokensDTO;

@Component
public class JwtTokenProvider {

    @Value("${api.security.token.secret}")
    private String secret;

    private final FindUsersUsecases findUsersUsecases;
    private final UpdateUsersUsecases updateUsersUsecases;
    private final UserMapper userMapper;

    @Autowired
    public JwtTokenProvider(FindUsersUsecases findUsersUsecases, UpdateUsersUsecases updateUsersUsecases, UserMapper userMapper) {
        this.findUsersUsecases = findUsersUsecases;
        this.updateUsersUsecases = updateUsersUsecases;
        this.userMapper = userMapper;
    }

    public TokensDTO generateTokens(UsersEntity usersEntity) {
        String accessToken = generateAccessToken(usersEntity);
        String refreshToken = generateRefreshToken(usersEntity);
        String apiKey = retrieveOrGenerateApiKey(usersEntity);
        return new TokensDTO(accessToken, refreshToken, usersEntity, apiKey);
    }

    public String retrieveOrGenerateApiKey(UsersEntity user) {
        if (StringUtils.hasText(user.getApiKey())) {
            return user.getApiKey();
        }
        String newApiKey = generateApiKey();
        user.setApiKey(newApiKey);
        updateUsersUsecases.execute(user.getEmail(), userMapper.toUser(user));
        return newApiKey;
    }

    private String generateApiKey() {
        byte[] keyBytes = new byte[32];
        new SecureRandom().nextBytes(keyBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(keyBytes);
    }

    public UserDetails validateApiKey(String apiKey) {
        UsersEntity user = userMapper.toUserEntity(findUsersUsecases.authApiKey(apiKey));
        return user; // Retorna a entidade de usuário completa (que implementa UserDetails)
    }

    public String validateAccessToken(String accessToken) {
        return validateToken(accessToken);
    }

    public String validateRefreshToken(String refreshToken) {
        return validateToken(refreshToken);
    }

    private String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    public String generateAccessToken(UsersEntity usersEntity) {
        return generateToken(usersEntity, genAccessTokenExpiry());
    }

    public String generateRefreshToken(UsersEntity usersEntity) {
        return generateToken(usersEntity, genRefreshTokenExpiry());
    }

    private String generateToken(UsersEntity usersEntity, Instant expiry) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(usersEntity.getEmail())
                    .withExpiresAt(expiry)
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar token", e);
        }
    }

    private Instant genAccessTokenExpiry() {
        return LocalDateTime.now().plusDays(90).toInstant(ZoneOffset.of("-03:00"));
    }

    private Instant genRefreshTokenExpiry() {
        return LocalDateTime.now().plusDays(365).toInstant(ZoneOffset.of("-03:00"));
    }
}