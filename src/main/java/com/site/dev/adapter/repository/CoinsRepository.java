package com.site.dev.adapter.repository;

import java.util.List;
import java.util.UUID;

import com.site.dev.core.domain.entity.Coins;
import org.springframework.data.jpa.repository.JpaRepository;

import com.site.dev.adapter.models.CoinsEntity;

public interface CoinsRepository extends JpaRepository<CoinsEntity, Long> {
    List<CoinsEntity> findAll();
    CoinsEntity findByUuid(UUID uuid);
    List<CoinsEntity> findBySymbol(String symbol);
    List<CoinsEntity> findByName(String name);
    
}
