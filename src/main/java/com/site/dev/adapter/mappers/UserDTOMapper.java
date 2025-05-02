package com.site.dev.adapter.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.site.dev.adapter.controllers.DTO.users.UsersRequest;
import com.site.dev.adapter.controllers.DTO.users.UsersResponse;
import com.site.dev.core.domain.entity.Users;
@Mapper(componentModel = "spring")
public interface UserDTOMapper {

    public Users toUser(UsersRequest createUserRequest);
   
    public UsersResponse toResponse(Users user);
    
    @Mapping(target = "password", ignore = true)
    public List<UsersResponse> toResponse(List<Users> users);
}
