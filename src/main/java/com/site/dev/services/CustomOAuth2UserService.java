package com.site.dev.services;

import com.site.dev.core.applications.usecases.users.CreateUsersUsecases;
import com.site.dev.core.applications.usecases.users.FindUsersUsecases;
import com.site.dev.core.domain.entity.Users;
import com.site.dev.core.domain.enums.UserRole;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private CreateUsersUsecases createUserUsecases;
    private FindUsersUsecases findUserUsecases;
    public CustomOAuth2UserService(
            CreateUsersUsecases createUserUsecases,
            FindUsersUsecases findUserUsecases
    ) {
        this.createUserUsecases = createUserUsecases;
        this.findUserUsecases = findUserUsecases;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest req) {
        OAuth2User user = super.loadUser(req);
        String email = user.getAttribute("email");
        if (findUserUsecases.execute(email) == null) {
            Users novo = new Users();
            novo.setEmail(email);
            novo.setName(user.getName());
            novo.setRole(UserRole.NORMAL);

            novo.setPassword(user.getName().replaceAll("\\s", "") + "@Senai");
            createUserUsecases.execute(novo);
        }

        return user;
    }
}
