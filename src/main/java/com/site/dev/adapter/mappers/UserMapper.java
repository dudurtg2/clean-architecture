package com.site.dev.adapter.mappers;

import java.util.List;
import org.mapstruct.Mapper;

import com.site.dev.adapter.models.UsersEntity;
import com.site.dev.core.domain.entity.Users;
@Mapper(componentModel = "spring")
public interface UserMapper {
    public Users toUser(UsersEntity userEntity);
    
    public UsersEntity toUserEntity(Users user);
    
    public List<Users> toResponse(List<UsersEntity> users);
}
