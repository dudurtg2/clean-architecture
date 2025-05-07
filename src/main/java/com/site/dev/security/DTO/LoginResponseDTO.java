package com.site.dev.security.dto;

import com.site.dev.core.domain.entity.Users;

public class LoginResponseDTO{
    private final String accessToken;
    private final String refreshToken;
    private Users users;

    public LoginResponseDTO(  Users users, TokensDTO tokens) {
        this.users = users;
        this.accessToken = tokens.getAccessToken();
        this.refreshToken = tokens.getRefreshToken();
    }
    public Users getUser() {
        return users;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
