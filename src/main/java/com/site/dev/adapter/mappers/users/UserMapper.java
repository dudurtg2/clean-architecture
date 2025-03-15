package com.site.dev.adapter.mappers.users;

import java.util.List;
import org.mapstruct.Mapper;
import com.site.dev.adapter.entity.UserEntity;
import com.site.dev.core.domain.entity.Users;
@Mapper(componentModel = "spring")
public interface UserMapper {
    public Users toUser(UserEntity userEntity);
    
    public UserEntity toUserEntity(Users user);
    
    public List<Users> toResponse(List<UserEntity> users);
}
