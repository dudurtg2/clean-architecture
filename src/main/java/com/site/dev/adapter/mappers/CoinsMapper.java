package com.site.dev.adapter.mappers;

import java.util.List;

import com.site.dev.adapter.controllers.dto.coins.CoinsResponse;
import org.mapstruct.Mapper;

import com.site.dev.adapter.controllers.dto.coins.CoinsRequest;
import com.site.dev.adapter.models.CoinsEntity;
import com.site.dev.core.domain.entity.Coins;
@Mapper(componentModel = "spring")
public interface CoinsMapper {
    public Coins toCoins(CoinsEntity coinsEntity);

    public Coins toCoins(CoinsRequest createCoinsRequest);
    
    public CoinsEntity toCoinsEntity(Coins coins);
    
    public List<CoinsEntity> toCoins(List<Coins> coins);
    public List<CoinsResponse> toResponses(List<Coins> coins);
    public CoinsResponse toResponse(Coins coins);

    public List<Coins> toRequest(List<CoinsEntity> coins);

}
