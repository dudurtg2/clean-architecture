package com.site.dev.core.applications.usecases.users;

import com.site.dev.core.applications.gateway.UsersGateWay;

public class DeleteUsersUsecases {
    private UsersGateWay userGateWay;
    private ValidadeUsers validadeUsers;

    public DeleteUsersUsecases(UsersGateWay userGateWay) {
        this.userGateWay = userGateWay;
        this.validadeUsers = new ValidadeUsers();
    }

    public void execute(String email){
        validadeUsers.verifyUserExists(email, userGateWay);
        userGateWay.delete(validadeUsers.getUser(email, userGateWay).getId());
    };

   
    
    

}
