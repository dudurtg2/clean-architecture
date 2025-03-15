package com.site.dev.core.applications.gateway;

import java.util.List;
import com.site.dev.core.domain.entity.Users;

public interface UsersGateWay {
    Users createUser(Users user);
    List<Users> getAllUsers();
    Users getUserById(Long id);
    Users getUserByEmail(String email);
}
