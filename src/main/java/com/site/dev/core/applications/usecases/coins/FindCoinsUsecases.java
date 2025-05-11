package com.site.dev.core.applications.usecases.coins;

import java.util.List;

import com.site.dev.core.applications.gateway.CoinsGateWay;
import com.site.dev.core.domain.entity.Coins;
import com.site.dev.core.domain.entity.Users;
import com.site.dev.core.domain.enums.TypeCoinSearch;

public class FindCoinsUsecases {
    private CoinsGateWay coinsGateWay;

    public FindCoinsUsecases(CoinsGateWay coinsGateWay) {
        this.coinsGateWay = coinsGateWay;
    }

    public List<Coins> execute(String index, TypeCoinSearch typeCoinSearch, Users users) {
        switch (typeCoinSearch) {
            case NAME:
                return coinsGateWay.getByName(index).stream().filter(coins -> coins.getUser().equals(users)).toList();
            case SYMBOL:
                return coinsGateWay.getBySymbol(index).stream().filter(coins -> coins.getUser().equals(users)).toList();
            default:
                return null;
        }
    }

    public Coins execute(Long id) {
        return coinsGateWay.getById(id);
    }

    public List<Coins> execute(Users users) {
        return coinsGateWay.getAll().stream().filter(coins -> coins.getUser().equals(users)).toList();
    }

}
