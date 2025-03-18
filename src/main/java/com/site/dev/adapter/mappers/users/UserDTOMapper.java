package com.site.dev.adapter.mappers.users;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.site.dev.adapter.controllers.DTO.users.CreateUserRequest;
import com.site.dev.adapter.controllers.DTO.users.CreateUserResponse;
import com.site.dev.core.domain.entity.Users;
@Mapper(componentModel = "spring")
public interface UserDTOMapper {

    public Users toUser(CreateUserRequest createUserRequest);
   
    public CreateUserResponse toResponse(Users user);
    
    @Mapping(target = "password", ignore = true)
    public List<CreateUserResponse> toResponse(List<Users> users);
}
