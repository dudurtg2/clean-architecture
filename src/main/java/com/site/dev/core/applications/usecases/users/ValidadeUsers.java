package com.site.dev.core.applications.usecases.users;

import com.site.dev.core.domain.entity.Users;
import com.site.dev.core.domain.exception.IncorretBoryUserException;
import com.site.dev.core.domain.exception.NoDuplicateUserException;
import com.site.dev.core.applications.gateway.UsersGateWay;

public class ValidadeUsers {
    
    public Users getUser(String email, UsersGateWay userGateWay ) {
        return userGateWay.getUserByEmail(email);
    }

    public void verifyUserExists(String email, UsersGateWay userGateWay) throws NoDuplicateUserException {
        Users existingUser = userGateWay.getUserByEmail(email);
        if (existingUser != null) {
            throw new NoDuplicateUserException();
        }
    }
    
    public void validateBory(Users user) throws IncorretBoryUserException {
        if (user.getName() == null || user.getEmail() == null || user.getPassword() == null) {
            throw new IncorretBoryUserException();
        }
            
    }

    public void validateNewBory(Users user, UsersGateWay userGateWay) throws IncorretBoryUserException {
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
