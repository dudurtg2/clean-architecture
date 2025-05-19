package com.site.dev.core.applications.usecases.users;

import com.site.dev.core.applications.gateway.UsersGateWay;
import com.site.dev.core.domain.entity.Users;
import com.site.dev.core.domain.exception.NoDuplicateEntityException;

public class DeleteUsersUsecases {
    private UsersGateWay userGateWay;

    public DeleteUsersUsecases(UsersGateWay userGateWay) {
        this.userGateWay = userGateWay;
    }

    public void execute(String email){
        verifyUserExists(email);
        userGateWay.delete(getUser(email).getUuid());
    };
    public void verifyUserExists(String email) {
        Users existingUser = userGateWay.getUserByEmail(email);
        if (existingUser != null) {
            throw new NoDuplicateEntityException("User");
        }
    }
   
    public Users getUser(String email) {
        return userGateWay.getUserByEmail(email);
    }

    
    

}
