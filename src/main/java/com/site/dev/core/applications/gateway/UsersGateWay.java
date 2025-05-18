package com.site.dev.core.applications.gateway;

import java.util.List;
import java.util.UUID;

import com.site.dev.core.domain.entity.Users;

public interface UsersGateWay {
    Users createUser(Users user);
    List<Users> getAllUsers();
    Users getUserByUUID(UUID uuid);
    Users getUserByEmail(String email);
    Users update(Users user);
    void delete(UUID uuid);
}
