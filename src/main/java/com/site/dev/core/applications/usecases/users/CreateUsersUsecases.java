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
        verifyUserExists(user.getEmail());
        return userGateWay.createUser(user.correct());
    };
    public void verifyUserExists(String email) {
        Users existingUser = userGateWay.getUserByEmail(email);
        if (existingUser != null) {
            throw new NoDuplicateEntityException("User");
        }
    }
}
