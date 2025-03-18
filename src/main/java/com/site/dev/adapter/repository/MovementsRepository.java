package com.site.dev.adapter.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.site.dev.adapter.entity.MovementsEntity;
import com.site.dev.core.domain.entity.Coins;
import com.site.dev.core.domain.enums.TypeCoins;

public interface MovementsRepository extends JpaRepository<MovementsEntity, Long> {
    List<MovementsEntity> findAll();
    List<MovementsEntity> findByTypeCoins(TypeCoins typeCoins);
    List<MovementsEntity> findByCoins(Coins coins);
}
