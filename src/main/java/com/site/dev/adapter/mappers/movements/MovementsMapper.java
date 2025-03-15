package com.site.dev.adapter.mappers.movements;

import java.util.List;
import org.mapstruct.Mapper;

import com.site.dev.adapter.entity.MovementsEntity;
import com.site.dev.core.domain.entity.Movements;
@Mapper(componentModel = "spring")
public interface MovementsMapper {
    public Movements toUser(MovementsEntity movementsEntity);
    
    public MovementsEntity toUserEntity(Movements movements);
    
    public List<Movements> toResponse(List<MovementsEntity> movementsEntity);
}
