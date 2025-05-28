package com.site.dev.adapter.mappers;

import java.util.List;

import com.site.dev.adapter.controllers.dto.users.UpdatesUsers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.site.dev.adapter.controllers.dto.users.UsersRequest;
import com.site.dev.adapter.controllers.dto.users.UsersResponse;
import com.site.dev.core.domain.entity.Users;
@Mapper(componentModel = "spring")
public interface UserDTOMapper {

    public Users toUser(UsersRequest createUserRequest);
   
    public UsersResponse toResponse(Users user);
    
    @Mapping(target = "password", ignore = true)
    public List<UsersResponse> toResponse(List<Users> users);

    public Users toUser(UpdatesUsers updatesUsers);
}
