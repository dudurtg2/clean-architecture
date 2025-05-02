package com.site.dev.core.applications.usecases.users;

import java.util.List;
import com.site.dev.core.applications.gateway.UsersGateWay;
import com.site.dev.core.domain.entity.Users;


public class FindUsersUsecases {
     private UsersGateWay userGateWay;

    
    public FindUsersUsecases(UsersGateWay userGateWay){
        this.userGateWay = userGateWay;
    }

    public Users execute(Long id){
        return userGateWay.getUserById(id);
    }

    public Users execute(String email){
        return userGateWay.getUserByEmail(email);
    }

    public List<Users> execute(){
        return userGateWay.getAllUsers();
    }
}
