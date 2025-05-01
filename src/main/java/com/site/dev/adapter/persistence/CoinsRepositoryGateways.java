package com.site.dev.adapter.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.site.dev.adapter.mappers.coins.CoinsMapper;
import com.site.dev.adapter.repository.CoinsRepository;
import com.site.dev.core.applications.gateway.CoinsGateWay;
import com.site.dev.core.domain.entity.Coins;
@Component
public class CoinsRepositoryGateways implements CoinsGateWay {
    private final CoinsRepository coinsRepository;
    private final CoinsMapper coinsMapper;

    @Autowired  
    public CoinsRepositoryGateways(CoinsRepository coinsRepository, CoinsMapper coinsMapper) {
        this.coinsRepository = coinsRepository;
        this.coinsMapper = coinsMapper;
    }

    @Override
    public Coins create(Coins coins) {
        return coinsMapper.toCoins(coinsRepository.save(coinsMapper.toCoinsEntity(coins)));
    }

    @Override
    public List<Coins> getAll() {
        return coinsMapper.toRequest(coinsRepository.findAll());
    }

    @Override
    public Coins getByName(String name) {
        return coinsMapper.toCoins(coinsRepository.findByName(name));
    }

    @Override
    public Coins getById(Long id) {
        return coinsMapper.toCoins(coinsRepository.findById(id).get());
    }

}
