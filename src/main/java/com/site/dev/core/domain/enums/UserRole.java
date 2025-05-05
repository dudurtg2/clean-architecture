package com.site.dev.core.domain.enums;


public enum UserRole {

    NORMAL("NORMAL"),
    PREMIUM("PREMIUM");

    private final String role;

  
    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}

