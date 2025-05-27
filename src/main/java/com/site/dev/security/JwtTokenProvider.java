package com.site.dev.security;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.site.dev.adapter.models.UsersEntity;
import com.site.dev.security.dto.TokensDTO;

@Component
public class JwtTokenProvider  {

    @Value("${api.security.token.secret}")
    private String secret;



    public String generateAccessToken(UsersEntity UsersEntity) {
        return generateToken(UsersEntity, genAccessTokenExpiry());
    }

    public String generateRefreshToken(UsersEntity UsersEntity) {
        return generateToken(UsersEntity, genRefreshTokenExpiry());
    }

    public TokensDTO generateTokens(UsersEntity usersEntity) {
        String accessToken = generateAccessToken(usersEntity);
        String refreshToken = generateRefreshToken(usersEntity);
        return new TokensDTO(accessToken, refreshToken, usersEntity);
    }

    public String validateAccessToken(String accessToken) {
        return validateToken(accessToken, "access");
    }

    public String validateRefreshToken(String refreshToken) {
        return validateToken(refreshToken, "refresh");
    }

    public String validateToken(String token, String type) {
        try {
            Algorithm algorithms = Algorithm.HMAC256(secret);
            return JWT.require(algorithms)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    private String generateToken(UsersEntity UsersEntity, Instant expiry) {
        try {
            Algorithm algorithms = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(UsersEntity.getEmail())
                    .withExpiresAt(expiry)
                    .sign(algorithms);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar token", e);
        }
    }

    private Instant genAccessTokenExpiry() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }

    private Instant genRefreshTokenExpiry() {
        return LocalDateTime.now().plusDays(15).toInstant(ZoneOffset.of("-03:00"));
    }

}
