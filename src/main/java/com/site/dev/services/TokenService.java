package com.site.dev.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;


import com.site.dev.core.domain.entity.Users;
import com.site.dev.security.DTO.TokensDTO;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateAccessToken(Users users) {
        return generateToken(users, genAccessTokenExpiry());
    }

    public String generateRefreshToken(Users users) {
        return generateToken(users, genRefreshTokenExpiry());
    }

    public TokensDTO generateTokens(Users users) {
        String accessToken = generateAccessToken(users);
        String refreshToken = generateRefreshToken(users);
        return new TokensDTO(accessToken, refreshToken);
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

            // Valida o token
            return JWT.require(algorithms)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    private String generateToken(Users users, Instant expiry) {
        try {
            Algorithm algorithms = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(users.getEmail())
                    .withExpiresAt(expiry)
                    .sign(algorithms);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar token", e);
        }
    }

    private Instant genAccessTokenExpiry() {
        return LocalDateTime.now().plusHours(12).toInstant(ZoneOffset.of("-03:00"));
    }

    private Instant genRefreshTokenExpiry() {
        return LocalDateTime.now().plusDays(30).toInstant(ZoneOffset.of("-03:00"));
    }

    

}
