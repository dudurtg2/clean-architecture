package com.site.dev.core.applications.usecases.users;

import com.site.dev.core.domain.entity.Users;
import com.site.dev.core.applications.gateway.UsersGateWay;

public class CreateUsersUsecases {
    private UsersGateWay userGateWay;
    private ValidadeUsers validadeUsers;

    public CreateUsersUsecases(UsersGateWay userGateWay) {
        this.userGateWay = userGateWay;
        this.validadeUsers = new ValidadeUsers();
    }

    public Users execute(Users user){
        validadeUsers.validateBory(user);
        validadeUsers.validateNewBory(user, userGateWay);
        return userGateWay.createUser(user);
    };

}
