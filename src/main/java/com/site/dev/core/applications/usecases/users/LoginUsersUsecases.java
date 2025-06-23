package com.site.dev.core.applications.usecases.users;

import com.site.dev.core.applications.gateway.UsersGateWay;
import com.site.dev.core.domain.entity.Users;
import com.site.dev.core.domain.enums.UserRole;

import java.util.List;
import java.util.UUID;


public class LoginUsersUsecases {
     private UsersGateWay userGateWay;

    public LoginUsersUsecases(UsersGateWay userGateWay){
        this.userGateWay = userGateWay;
    }


    public Users execute(String sub, String email, String nome) {

        Users user = userGateWay.getUserByEmail(email);

        if(user == null) {
           return userGateWay.createUser((Users.builder()
                    .email(email)
                    .name(nome)
                    .sub(sub)
                    .role(UserRole.NORMAL)
                    .password(sub)
                    .build()));
        }

        if(sub == null || sub.isBlank()) {
            throw new IllegalArgumentException("Sub cannot be null or blank");
        }
        if(user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (sub.equals(user.getSub())) {
            return user;
        }
        throw new IllegalArgumentException("User sub does not match the provided sub");
    }

}
