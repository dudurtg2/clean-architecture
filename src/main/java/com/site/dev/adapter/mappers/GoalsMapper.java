package com.site.dev.adapter.mappers;

import com.site.dev.adapter.controllers.dto.movements.MovementsRequest;
import com.site.dev.adapter.controllers.dto.movements.MovementsResponse;
import com.site.dev.adapter.models.GoalsEntity;
import com.site.dev.adapter.models.MovementsEntity;
import com.site.dev.core.domain.entity.Goals;
import com.site.dev.core.domain.entity.Movements;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GoalsMapper {

    public Goals toGoals(GoalsEntity goalsEntity);
    
    public GoalsEntity toGoalsEntity(Goals goals);

    public List<Goals> toGoals(List<GoalsEntity> goalsEntity);

    public List<GoalsEntity> toGoalsEntity(List<Goals> goals);

}
