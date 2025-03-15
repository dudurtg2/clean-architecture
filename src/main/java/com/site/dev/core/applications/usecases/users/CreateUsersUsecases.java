package com.site.dev.core.applications.usecases.users;

import com.site.dev.core.domain.entity.Users;
import com.site.dev.core.domain.exception.IncorretBoryUserException;
import com.site.dev.core.domain.exception.NoDuplicateUserException;
import com.site.dev.core.applications.gateway.UsersGateWay;

public class CreateUsersUsecases {
    private UsersGateWay userGateWay;

    public CreateUsersUsecases(UsersGateWay userGateWay){
        this.userGateWay = userGateWay;
    }

    public Users execute(Users user){
        validate(user);
        verifyUserExists(user);
        return userGateWay.createUser(user);
    };

    public void verifyUserExists(Users user) throws NoDuplicateUserException {
        Users existingUser = userGateWay.getUserByEmail(user.getEmail());
        if (existingUser != null) {
            throw new NoDuplicateUserException();
        }
    }
    
    public void validate(Users user) throws IncorretBoryUserException {
        if (user.getName() == null || user.getEmail() == null || user.getPassword() == null) {
            throw new IncorretBoryUserException();
        }
            
    }
}
