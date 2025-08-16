package com.site.dev.security.dto;

import com.site.dev.adapter.models.UsersEntity;

public class TokensDTO {
    private final String accessToken;
    private final String refreshToken;
    private final String apiKey;
    private final UsersEntity user;

    public TokensDTO(String accessToken, String refreshToken, UsersEntity user, String apiKey) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.apiKey = apiKey;
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
    public String getApiKey() {
        return apiKey;
    }
}