package com.site.dev.adapter.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.site.dev.adapter.controllers.DTO.response.CreateUserResponse;
import com.site.dev.adapter.controllers.DTO.resquest.CreateUserRequest;
import com.site.dev.core.domain.entity.User;
@Mapper(componentModel = "spring")
public interface UserDTOMapper {

    public User toUser(CreateUserRequest createUserRequest);
   
    @Mapping(target = "password", ignore = true)
    public CreateUserResponse toResponse(User user);
    
    @Mapping(target = "password", ignore = true)
    public List<CreateUserResponse> toResponse(List<User> users);
}
