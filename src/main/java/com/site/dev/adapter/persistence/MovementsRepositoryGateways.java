package com.site.dev.adapter.persistence;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.site.dev.adapter.mappers.MovementsMapper;
import com.site.dev.adapter.repository.MovementsRepository;
import com.site.dev.core.applications.gateway.MovementsGateWay;
import com.site.dev.core.domain.entity.Coins;
import com.site.dev.core.domain.entity.Movements;
import com.site.dev.core.domain.enums.TypeCoins;
@Component
public class MovementsRepositoryGateways implements MovementsGateWay {
    private final MovementsRepository movementsRepository;
    private final MovementsMapper movementsMapper;
    
    @Autowired
    public MovementsRepositoryGateways(MovementsRepository movementsRepository, MovementsMapper movementsMapper) {
        this.movementsRepository = movementsRepository;
        this.movementsMapper = movementsMapper;
    }

    @Override
    public List<Movements> getAll() {
        return movementsMapper.toResponse(movementsRepository.findAll());
    }

    @Override
    public List<Movements> getByCoins(Coins coins) {
        return movementsMapper.toResponse(movementsRepository.findByCoins(coins));
    }

    @Override
    public List<Movements> getByTypeCoins(TypeCoins typeCoins) {
        return movementsMapper.toResponse(movementsRepository.findByTypeCoins(typeCoins));
    }

    @Override
    public Movements geyByUUID(String uuid) {
        return movementsMapper.toMovements(movementsRepository.findByUuid(uuid));
    }

    @Override
    public Movements create(Movements movements) {
        return movementsMapper.toMovements(movementsRepository.save(movementsMapper.toMovementsEntity(movements)));
    }

    @Override
    public void delete(String uuid) {
        movementsRepository.delete(movementsRepository.findByUuid(uuid));
    }

    @Override
    public Movements update(Movements movements) {
        return movementsMapper.toMovements(movementsRepository.save(movementsMapper.toMovementsEntity(movements)));
    }
   
}