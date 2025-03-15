package com.site.dev.core.applications.gateway;

import java.util.List;

import com.site.dev.core.domain.entity.Coins;
import com.site.dev.core.domain.entity.Movements;
import com.site.dev.core.domain.enums.TypeCoins;

public interface MovementsGateWay {
    Movements create(Movements movements);
    List<Movements> getAll();
    List<Movements> getByCoins(Coins coins);
    List<Movements> getByTypeCoins(TypeCoins typeCoins);
    Movements getById(Long id);
    
}
