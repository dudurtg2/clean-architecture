package com.site.dev.adapter.persistence;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.site.dev.adapter.mappers.CoinsMapper;
import com.site.dev.adapter.mappers.UserMapper;
import com.site.dev.adapter.models.CoinsEntity;
import com.site.dev.adapter.repository.CoinsRepository;
import com.site.dev.core.applications.gateway.CoinsGateWay;
import com.site.dev.core.domain.entity.Coins;
@Component
public class CoinsRepositoryGateways implements CoinsGateWay {
    private final CoinsRepository coinsRepository;
    private final CoinsMapper coinsMapper;
    private  UserRepositoryGateways userRepositoryGateways;
    private  UserMapper userMapper;

    @Autowired  
    public CoinsRepositoryGateways(CoinsRepository coinsRepository, CoinsMapper coinsMapper, UserRepositoryGateways userRepositoryGateways, UserMapper userMapper) {
        this.coinsRepository = coinsRepository;
        this.coinsMapper = coinsMapper;
        this.userRepositoryGateways = userRepositoryGateways;
        this.userMapper = userMapper;
    }

    @Override
    public Coins create(Coins coins) {

        CoinsEntity coinsEntity = coinsMapper.toCoinsEntity(coins);
        coinsEntity.setUser(userMapper.toUserEntity(userRepositoryGateways.geyUserByUUID(coinsEntity.getUser().getUuid())));

        return coinsMapper.toCoins(coinsRepository.save(coinsEntity));
    }

    @Override
    public List<Coins> getAll() {
        return coinsMapper.toRequest(coinsRepository.findAll());
    }

    @Override
    public List<Coins> getByName(String name) {
        return coinsMapper.toRequest(coinsRepository.findByName(name));
    }

    @Override
    public List<Coins> getBySymbol(String symbol) {
        return coinsMapper.toRequest(coinsRepository.findBySymbol(symbol));
    }

    @Override
    public Coins getByUUID(String uuid) {
        return coinsMapper.toCoins(coinsRepository.findByUuid(uuid));
    }

    @Override
    public Coins update(Coins coins) {
        return coinsMapper.toCoins(coinsRepository.save(coinsMapper.toCoinsEntity(coins)));
    }

    @Override
    public void delete(String uuid) {
        coinsRepository.delete(coinsRepository.findByUuid(uuid));
    }

}
