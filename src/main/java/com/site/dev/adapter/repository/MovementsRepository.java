package com.site.dev.adapter.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.site.dev.adapter.models.MovementsEntity;
import com.site.dev.core.domain.entity.Coins;
import com.site.dev.core.domain.enums.TipoDespesa;

public interface MovementsRepository extends JpaRepository<MovementsEntity, Long> {
    List<MovementsEntity> findAll();
    MovementsEntity findByUuid(UUID uuid);
    List<MovementsEntity> findByTipoDespesa(TipoDespesa tipoDespesa);
    List<MovementsEntity> findByCoins(Coins coins);
    
}
