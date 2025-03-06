package com.site.dev.core.applications.usecases;

import com.site.dev.core.domain.entity.User;
import com.site.dev.core.domain.exception.CustomException;
import com.site.dev.core.applications.gateway.UserGateWay;

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
        if (user.getName().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
            throw new CustomException("Campos obrigatórios não preenchidos", 400);
        }
            
    }
}
