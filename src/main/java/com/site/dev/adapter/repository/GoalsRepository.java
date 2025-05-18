package com.site.dev.adapter.repository;

import com.site.dev.adapter.models.GoalsEntity;
import com.site.dev.core.domain.entity.Coins;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GoalsRepository extends JpaRepository<GoalsEntity, Long> {
    List<GoalsEntity> findAll();
    GoalsEntity findByUuid(UUID uuid);
    List<GoalsEntity> findByCoins(Coins coins);
    
}
