package com.site.dev.adapter.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.site.dev.adapter.entity.CoinsEntity;

public interface CoinsRepository extends JpaRepository<CoinsEntity, Long> {
    List<CoinsEntity> findAll();
    CoinsEntity findBySymbol(String symbol);
    CoinsEntity findByName(String name);
    
}
