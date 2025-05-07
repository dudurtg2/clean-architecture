package com.site.dev.core.applications.usecases.coins;

import java.util.List;

import com.site.dev.core.applications.gateway.CoinsGateWay;
import com.site.dev.core.domain.entity.Coins;
import com.site.dev.core.domain.enums.TypeCoinSearch;

public class FindCoinsUsecases {
    private CoinsGateWay coinsGateWay;

    public FindCoinsUsecases(CoinsGateWay coinsGateWay) {
        this.coinsGateWay = coinsGateWay;
    }

    public List<Coins> execute(String index, TypeCoinSearch typeCoinSearch) {
        switch (typeCoinSearch) {
            case NAME:
                return coinsGateWay.getByName(index);
            case SYMBOL:
                return coinsGateWay.getBySymbol(index);
            default:
                return null;
        }
    }

    public Coins execute(Long id) {
        return coinsGateWay.getById(id);
    }

    public List<Coins> execute() {
        return coinsGateWay.getAll();
    }

}
