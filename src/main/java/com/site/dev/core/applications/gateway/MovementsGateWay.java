package com.site.dev.core.applications.gateway;

import java.util.List;
import java.util.UUID;

import com.site.dev.core.domain.entity.Coins;
import com.site.dev.core.domain.entity.Movements;
import com.site.dev.core.domain.enums.TipoDespesa;

public interface MovementsGateWay {
    Movements create(Movements movements);
    List<Movements> getAll();
    List<Movements> getByCoins(Coins coins);
    List<Movements> getByTipoDespesa(TipoDespesa tipoDespesa);
    Movements getByUUID(UUID uuid);
    void delete(UUID uuid);
    Movements update(Movements movements);

    
}
