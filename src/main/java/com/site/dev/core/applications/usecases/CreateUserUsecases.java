package com.site.dev.core.applications.usecases;

import com.site.dev.core.domain.entity.User;
import com.site.dev.core.domain.exception.IncorretBoryUserException;
import com.site.dev.core.domain.exception.NoDuplicateUserException;
import com.site.dev.core.applications.gateway.UserGateWay;

public class CreateUserUsecases {
    private UserGateWay userGateWay;

    
    public CreateUserUsecases(UserGateWay userGateWay){
        this.userGateWay = userGateWay;
    }

    public User execute(User user){
        boryValidate(user);
        verifyUserExists(user);
        return userGateWay.createUser(user);
    };

    public void verifyUserExists(User user) throws NoDuplicateUserException {
        User existingUser = userGateWay.getUserByEmail(user.getEmail());
        if (existingUser != null) {
            throw new NoDuplicateUserException();
        }
    }
    
    public void boryValidate(User user) throws IncorretBoryUserException {
        if (user.getName() == null || user.getEmail() == null || user.getPassword() == null) {
            throw new IncorretBoryUserException();
        }
            
    }
}
