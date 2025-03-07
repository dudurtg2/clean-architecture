package com.site.dev.core.applications.gateway;

import java.util.List;

import com.site.dev.core.domain.entity.User;

public interface UserGateWay {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    User getUserByEmail(String email);
}
