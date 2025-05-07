package com.site.dev.adapter.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.site.dev.adapter.models.CoinsEntity;

public interface CoinsRepository extends JpaRepository<CoinsEntity, Long> {
    List<CoinsEntity> findAll();
    List<CoinsEntity> findBySymbol(String symbol);
    List<CoinsEntity> findByName(String name);
    
}
