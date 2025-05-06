package com.site.dev.core.applications.gateway;

import java.util.List;

import com.site.dev.core.domain.entity.Coins;

public interface CoinsGateWay {
    Coins create(Coins coins);
    List<Coins> getAll();
    Coins getByName(String name);
    Coins getById(Long id);
    Coins update(Coins coins);
    void delete(Long id);
}
