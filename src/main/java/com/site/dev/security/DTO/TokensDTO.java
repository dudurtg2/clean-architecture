package com.site.dev.security.dto;

import com.site.dev.adapter.models.UsersEntity;
import org.apache.catalina.User;

public class TokensDTO {
    private final String accessToken;
    private final String refreshToken;
    private final UsersEntity user;

    public TokensDTO(String accessToken, String refreshToken, UsersEntity user) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public UsersEntity getUser() {
        return user;
    }
}