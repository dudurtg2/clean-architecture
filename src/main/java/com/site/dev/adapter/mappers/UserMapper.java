package com.site.dev.adapter.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.site.dev.adapter.entity.UserEntity;
import com.site.dev.core.domain.entity.User;
@Mapper(componentModel = "spring")
public interface UserMapper {
    public User toUser(UserEntity userEntity);
    
    public UserEntity toUserEntity(User user);
    
    public List<User> toResponse(List<UserEntity> users);
}
