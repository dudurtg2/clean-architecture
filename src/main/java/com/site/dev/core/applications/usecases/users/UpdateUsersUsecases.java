package com.site.dev.core.applications.usecases.users;

import com.site.dev.core.domain.entity.Users;
import com.site.dev.core.domain.exception.IncorretBoryUserException;
import com.site.dev.core.domain.exception.NoDuplicateUserException;
import com.site.dev.core.applications.gateway.UsersGateWay;

public class UpdateUsersUsecases {
    private UsersGateWay userGateWay;

    public UpdateUsersUsecases(UsersGateWay userGateWay){
        this.userGateWay = userGateWay;
    }

    public Users execute(Users user){
        validate(user);
        verifyUserExists(user);
        return userGateWay.update(user);
    };

    public void verifyUserExists(Users user) throws NoDuplicateUserException {
        Users existingUser = userGateWay.getUserByEmail(user.getEmail());
        if (existingUser != null) {
            throw new NoDuplicateUserException();
        }
    }
    
    public void validate(Users user) throws IncorretBoryUserException {
        if (user == null) {
            throw new IncorretBoryUserException();
        }
        
        Users existingUser = userGateWay.getUserByEmail(user.getEmail());
        if (existingUser == null) {
            throw new IncorretBoryUserException();
        }
        
        user.setName(user.getName() == null ? existingUser.getName() : user.getName());
        user.setEmail(user.getEmail() == null ? existingUser.getEmail() : user.getEmail());
        user.setPassword(user.getPassword() == null ? existingUser.getPassword() : user.getPassword());
        user.setRole(null == user.getRole() ? existingUser.getRole() : user.getRole());
        
    }
}
