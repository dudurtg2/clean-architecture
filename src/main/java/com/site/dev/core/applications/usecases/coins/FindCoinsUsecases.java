package com.site.dev.core.applications.usecases.coins;

import java.util.List;

import com.site.dev.core.applications.gateway.CoinsGateWay;
import com.site.dev.core.domain.entity.Coins;

public class FindCoinsUsecases {
    private CoinsGateWay coinsGateWay;

    public FindCoinsUsecases(CoinsGateWay coinsGateWay) {
        this.coinsGateWay = coinsGateWay;
    }

    public Coins execute(String name) {
        return coinsGateWay.getByName(name);
    }

    public Coins execute(Long id) {
        return coinsGateWay.getById(id);
    }

    public List<Coins> execute() {
        return coinsGateWay.getAll();
    }
}
