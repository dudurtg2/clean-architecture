package com.site.dev.adapter.mappers;


import com.site.dev.adapter.controllers.dto.goals.GoalsRequest;
import com.site.dev.adapter.controllers.dto.goals.GoalsResponce;
import com.site.dev.adapter.models.GoalsEntity;
import com.site.dev.core.domain.entity.Goals;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface GoalsMapper {

    public Goals toGoals(GoalsEntity goalsEntity);
    public Goals toGoals(GoalsRequest goalsRequest);
    public GoalsResponce toResponce(Goals goals);
    public List<GoalsResponce> toResponce(List<Goals> goals);

    public GoalsEntity toGoalsEntity(Goals goals);

    public List<Goals> toGoals(List<GoalsEntity> goalsEntity);

    public List<GoalsEntity> toGoalsEntity(List<Goals> goals);

}
