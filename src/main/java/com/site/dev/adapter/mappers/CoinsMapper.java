package com.site.dev.adapter.mappers;

import java.util.List;
import org.mapstruct.Mapper;

import com.site.dev.adapter.controllers.dto.coins.CoinsRequest;
import com.site.dev.adapter.models.CoinsEntity;
import com.site.dev.core.domain.entity.Coins;
@Mapper(componentModel = "spring")
public interface CoinsMapper {
    public Coins toCoins(CoinsEntity coinsEntity);
    public Coins toCoins(CoinsRequest createCoinsRequest);
    
    public CoinsEntity toCoinsEntity(Coins coins);
    
    public List<CoinsEntity> toResponse(List<Coins> coins);

    public List<Coins> toRequest(List<CoinsEntity> coins);

}
