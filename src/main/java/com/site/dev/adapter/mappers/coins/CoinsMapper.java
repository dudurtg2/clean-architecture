package com.site.dev.adapter.mappers.coins;

import java.util.List;
import org.mapstruct.Mapper;

import com.site.dev.adapter.entity.CoinsEntity;
import com.site.dev.core.domain.entity.Coins;
@Mapper(componentModel = "spring")
public interface CoinsMapper {
    public Coins toCoins(CoinsEntity coinsEntity);
    
    public CoinsEntity toCoinsEntity(Coins coins);
    
    public List<Coins> toResponse(List<CoinsEntity> coinsEntity);
}
