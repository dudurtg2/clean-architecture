package com.site.dev.core.applications.gateway;

import com.site.dev.core.domain.entity.Coins;
import com.site.dev.core.domain.entity.Goals;
import com.site.dev.core.domain.entity.Movements;
import com.site.dev.core.domain.enums.TypeCoins;

import java.util.List;
import java.util.UUID;

public interface GoalsGateWay {
    Goals create(Goals goals);
    List<Goals> getAll();
    List<Goals> getByCoins(Coins coins);
    Goals getByUUID(UUID uuid);
    void delete(UUID uuid);
    Goals update(Goals goals);
}
