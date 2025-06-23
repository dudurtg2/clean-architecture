package com.site.dev.core.applications.usecases.users;


import com.site.dev.core.applications.gateway.UsersGateWay;
import com.site.dev.core.domain.entity.Users;
import com.site.dev.core.domain.enums.UserRole;

public class LoginUsersUsecases {
    private UsersGateWay userGateWay;

    public LoginUsersUsecases(UsersGateWay userGateWay) {
        this.userGateWay = userGateWay;
    }

    public Users google(String sub, String email, String nome) {
        Users user = userGateWay.getUserByEmail(email);

        if (user == null) {
            throw new IllegalArgumentException("User not found with email: " + email);
        }
        return userGateWay.createUser((Users.builder()
                .email(email)
                .name(nome)
                .sub(sub)
                .role(UserRole.NORMAL)
                .password(sub)
                .build()));
    }

    public Users execute(String sub, String email, String nome) {

        Users user = userGateWay.getUserByEmail(email);

        if (user == null) {
            throw new IllegalArgumentException("User not found with email: " + email);
        }

        if (sub == null || sub.isBlank()) {
            throw new IllegalArgumentException("Sub cannot be null or blank");
        }
       
        if (sub.equals(user.getSub())) {
            return user;
        }
        throw new IllegalArgumentException("User sub does not match the provided sub");
    }

}
