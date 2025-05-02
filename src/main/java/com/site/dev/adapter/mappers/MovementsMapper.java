package com.site.dev.adapter.mappers;

import java.util.List;
import org.mapstruct.Mapper;

import com.site.dev.adapter.controllers.DTO.movements.MovementsRequest;
import com.site.dev.adapter.controllers.DTO.movements.MovementsResponse;
import com.site.dev.adapter.entity.MovementsEntity;
import com.site.dev.core.domain.entity.Movements;
@Mapper(componentModel = "spring")
public interface MovementsMapper {
    public Movements toMovements(MovementsRequest createMovementsRequest);
    public Movements toMovements(MovementsEntity movementsEntity);
    
    public MovementsEntity toMovementsEntity(Movements movements);
    
    public List<Movements> toResponses(List<MovementsRequest> createMovementsRequest);
    public List<Movements> toResponse(List<MovementsEntity> movementsEntity);
    public List<MovementsResponse> toResponseEntity(List<Movements> movements);
    public MovementsResponse toResponse(Movements movements);
}
