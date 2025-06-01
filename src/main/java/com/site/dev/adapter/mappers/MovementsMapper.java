package com.site.dev.adapter.mappers;

import java.util.List;

import com.site.dev.adapter.controllers.dto.movements.MovementsRequest;
import org.mapstruct.Mapper;

import com.site.dev.adapter.models.MovementsEntity;
import com.site.dev.core.domain.entity.Movements;
@Mapper(componentModel = "spring")
public interface MovementsMapper {
    public Movements toMovements(MovementsEntity movementsEntity);
    public Movements toMovements(MovementsRequest movementsRequest);
    public MovementsEntity toMovementsEntity(Movements movements);
    public List<Movements> toResponse(List<MovementsEntity> movementsEntity);

}
