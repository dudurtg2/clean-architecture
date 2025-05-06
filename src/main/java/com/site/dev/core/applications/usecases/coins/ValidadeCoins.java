package com.site.dev.core.applications.usecases.coins;

import com.site.dev.core.applications.gateway.CoinsGateWay;
import com.site.dev.core.domain.entity.Coins;
import com.site.dev.core.domain.exception.IncorretBoryUserException;
import com.site.dev.core.domain.exception.NotExistsEntityException;

public class ValidadeCoins {
    
    public void verifyCoinsExists(Long id, CoinsGateWay coinsGateWay) {
        if(coinsGateWay.getById(id) == null) throw new NotExistsEntityException("Coins");
    }
    public void validateNewBory(Coins coins, CoinsGateWay coinsGateWay) {
        Coins coinsInBD = coinsGateWay.getById(coins.getId());
        coins.setImage(coins.getImage() == null ? coinsInBD.getImage() : coins.getImage());
        coins.setName(coins.getName() == null ? coinsInBD.getName() : coins.getName());
        coins.setSymbol(coins.getSymbol() == null ? coinsInBD.getSymbol() : coins.getSymbol());
        coins.setUser(coins.getUser() == null ? coinsInBD.getUser() : coins.getUser());
    } 
    public void validateBory(Coins coins) {
        if (coins.getName() == null || coins.getSymbol() == null || coins.getImage() == null) throw new IncorretBoryUserException();
    } 
}
