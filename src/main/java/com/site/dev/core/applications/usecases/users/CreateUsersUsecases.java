package com.site.dev.core.applications.usecases.users;

import com.site.dev.core.domain.entity.Users;
import com.site.dev.core.domain.exception.NoDuplicateEntityException;
import com.site.dev.core.applications.gateway.UsersGateWay;

public class CreateUsersUsecases {
    private UsersGateWay userGateWay;

    public CreateUsersUsecases(UsersGateWay userGateWay) {
        this.userGateWay = userGateWay;
    }

    public Users execute(Users user){
        Users existingUser = userGateWay.getUserByEmail(user.getEmail());
        if (existingUser != null) {
            return existingUser;
        }

        user.setPassword(userGateWay.cryptPassword(user.getPassword()));

        return userGateWay.createUser(user.correct());
    }
}
