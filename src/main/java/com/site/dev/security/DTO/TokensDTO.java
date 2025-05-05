package com.site.dev.security.DTO;

public class TokensDTO {
    private final String accessToken;
    private final String refreshToken;

    public TokensDTO(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}