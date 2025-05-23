package com.site.dev.core.applications.gateway;

import java.util.List;
import java.util.UUID;

import com.site.dev.core.domain.entity.Coins;

public interface CoinsGateWay {
    Coins create(Coins coins);
    List<Coins> getAll();
    List<Coins> getByName(String name);
    Coins getByUUID(String uuid);
    Coins update(Coins coins);
    void delete(String uuid);
    List<Coins> getBySymbol(String symbol);
}
