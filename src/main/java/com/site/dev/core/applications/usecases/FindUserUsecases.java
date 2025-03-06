package com.site.dev.core.applications.usecases;

import java.util.List;

import com.site.dev.core.applications.gateway.UserGateWay;
import com.site.dev.core.domain.entity.User;


public class FindUserUsecases {
     private UserGateWay userGateWay;

    
    public FindUserUsecases(UserGateWay userGateWay){
        this.userGateWay = userGateWay;
    }

    public User execute(Long id){
        return userGateWay.getUserById(id);
    }

    public List<User> execute(){
        return userGateWay.getAllUsers();
    }
}
