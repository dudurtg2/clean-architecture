package com.site.dev.adapter.persistence;

import java.util.List;
import java.util.UUID;

import com.site.dev.adapter.mappers.CoinsMapper;
import com.site.dev.adapter.models.CoinsEntity;
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
    private final CoinsMapper coinsMapper;
    
    @Autowired
    public MovementsRepositoryGateways(MovementsRepository movementsRepository, MovementsMapper movementsMapper,
            CoinsMapper coinsMapper) {
        this.movementsRepository = movementsRepository;
        this.movementsMapper = movementsMapper;
        this.coinsMapper = coinsMapper;
    }

    @Override
    public List<Movements> getAll() {
        return movementsMapper.toResponse(movementsRepository.findAll());
    }

    @Override
    public List<Movements> getByCoins(Coins coins) {

        return movementsMapper.toResponse(movementsRepository.findByCoins(coinsMapper.toCoinsEntity(coins) ));
    }



    @Override
    public Movements getByUUID(UUID uuid) {
        return movementsMapper.toMovements(movementsRepository.findByUuid(uuid));
    }

    @Override
    public Movements create(Movements movements) {
        return movementsMapper.toMovements(movementsRepository.save(movementsMapper.toMovementsEntity(movements)));
    }

    @Override
    public void delete(UUID uuid) {
        movementsRepository.delete(movementsRepository.findByUuid(uuid));
    }

    @Override
    public Movements update(Movements movements) {
        return movementsMapper.toMovements(movementsRepository.save(movementsMapper.toMovementsEntity(movements)));
    }
   
}