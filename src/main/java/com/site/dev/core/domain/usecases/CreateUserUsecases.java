package com.site.dev.core.domain.usecases;

import com.site.dev.core.domain.entity.User;
import com.site.dev.core.domain.exception.CustomException;
import com.site.dev.core.gateways.UserGateWay;

public class CreateUserUsecases {
    private UserGateWay userGateWay;

    
    public CreateUserUsecases(UserGateWay userGateWay){
        this.userGateWay = userGateWay;
    }

    public User execute(User user){
        validateUser(user);
        return userGateWay.createUser(user);
    };

    public void validateUser(User user) throws CustomException {
        if (user == null) {
            throw new CustomException("User cannot be null", 400);
        }
        if (user.getName() == null) {
            throw new CustomException("User name cannot be null", 400);
        }
        if (user.getEmail() == null) {
            throw new CustomException("User email cannot be null", 400);
        }
        if (user.getPassword() == null) {
            throw new CustomException("User password cannot be null", 400);
        }
    }
}
