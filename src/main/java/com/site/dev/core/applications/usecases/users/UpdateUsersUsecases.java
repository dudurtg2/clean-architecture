package com.site.dev.core.applications.usecases.users;

import com.site.dev.core.domain.entity.Users;
import com.site.dev.core.domain.exception.IncorrectBodyException;
import com.site.dev.core.domain.exception.NoDuplicateUserException;
import com.site.dev.core.applications.gateway.UsersGateWay;

public class UpdateUsersUsecases {
    private UsersGateWay userGateWay;

    public UpdateUsersUsecases(UsersGateWay userGateWay) {
        this.userGateWay = userGateWay;
    }

    public Users execute(String email, Users user){
        verifyUserExists(email);
        validateNewBory(user);

        return userGateWay.update(user);
    };

    public void verifyUserExists(String email) {
        Users existingUser = userGateWay.getUserByEmail(email);
        if (existingUser != null) {
            throw new NoDuplicateUserException();
        }
    }
   
    public void validateNewBory(Users user) throws IncorrectBodyException {
        if (user == null) {
            throw new IncorrectBodyException();
        }
        
        Users existingUser = userGateWay.getUserByEmail(user.getEmail());
        if (existingUser == null) {
            throw new IncorrectBodyException();
        }
        
        user.setName(user.getName() == null ? existingUser.getName() : user.getName());
        user.setEmail(user.getEmail() == null ? existingUser.getEmail() : user.getEmail());
        user.setPassword(user.getPassword() == null ? existingUser.getPassword() : user.getPassword());
        user.setRole(null == user.getRole() ? existingUser.getRole() : user.getRole());
        user.setDataNascimento(null == user.getDataNascimento() ? existingUser.getDataNascimento() : user.getDataNascimento());
        user.setGenero(null == user.getGenero() ? existingUser.getGenero() : user.getGenero());
        user.setCpf(null == user.getCpf() ? existingUser.getCpf() : user.getCpf());
        user.setTelefone(null == user.getTelefone() ? existingUser.getTelefone() : user.getTelefone());
        
    }
}
