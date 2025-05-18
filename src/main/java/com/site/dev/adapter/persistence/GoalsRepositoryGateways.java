package com.site.dev.adapter.persistence;

import com.site.dev.adapter.mappers.GoalsMapper;
import com.site.dev.adapter.mappers.MovementsMapper;
import com.site.dev.adapter.repository.GoalsRepository;
import com.site.dev.adapter.repository.MovementsRepository;
import com.site.dev.core.applications.gateway.GoalsGateWay;
import com.site.dev.core.applications.gateway.MovementsGateWay;
import com.site.dev.core.domain.entity.Coins;
import com.site.dev.core.domain.entity.Goals;
import com.site.dev.core.domain.entity.Movements;
import com.site.dev.core.domain.enums.TypeCoins;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class GoalsRepositoryGateways implements GoalsGateWay {
    private final GoalsRepository goalsRepository;
    private final GoalsMapper goalsMapper;

    @Autowired
    public GoalsRepositoryGateways(GoalsRepository goalsRepository, GoalsMapper goalsMapper) {
        this.goalsRepository = goalsRepository;
        this.goalsMapper = goalsMapper;
    }
    @Override
   public List<Goals> getAll() {
        return goalsMapper.toGoals(goalsRepository.findAll());
    }

    @Override
    public List<Goals> getByCoins(Coins coins) {
        return goalsMapper.toGoals(goalsRepository.findByCoins(coins));
    }

    @Override
    public Goals getByUUID(UUID uuid) {
        return goalsMapper.toGoals(goalsRepository.findByUuid(uuid));
    }

    @Override
    public Goals create(Goals goals) {
        return goalsMapper.toGoals(goalsRepository.save(goalsMapper.toGoalsEntity(goals)));
    }

    @Override
    public void delete(UUID uuid) {
        goalsRepository.delete(goalsRepository.findByUuid(uuid));
    }

    @Override
    public Goals update(Goals goals) {
        return goalsMapper.toGoals(goalsRepository.save(goalsMapper.toGoalsEntity(goals)));
    }
   
}