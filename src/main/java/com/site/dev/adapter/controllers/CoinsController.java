package com.site.dev.adapter.controllers;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.site.dev.adapter.controllers.dto.coins.CoinsRequest;
import com.site.dev.adapter.mappers.CoinsMapper;
import com.site.dev.adapter.models.CoinsEntity;
import com.site.dev.adapter.models.ExceptionBody;
import com.site.dev.core.applications.usecases.coins.CreateCoinsUsecases;
import com.site.dev.core.applications.usecases.coins.DeleteCoinsUsecases;
import com.site.dev.core.applications.usecases.coins.FindCoinsUsecases;
import com.site.dev.core.applications.usecases.coins.UpdateCoinsUsecases;
import com.site.dev.core.applications.usecases.users.FindUsersUsecases;
import com.site.dev.core.domain.entity.Coins;
import com.site.dev.core.domain.enums.TypeCoinSearch;
import com.site.dev.services.CollectEmailForTokenService;

@RestController
@RequestMapping("/api/coins")
public class CoinsController {

    private final CreateCoinsUsecases createCoinsUsecases;
    private final FindCoinsUsecases findCoinsUsecases;
    private final UpdateCoinsUsecases updateCoinsUsecases;
    private final DeleteCoinsUsecases deleteCoinsUsecases;
    private final CoinsMapper coinsMapper;
    private FindUsersUsecases findUsersUsecases;
    private CollectEmailForTokenService collectEmailForTokenService;

    @Autowired
    public CoinsController(CreateCoinsUsecases createCoinsUsecases, FindCoinsUsecases findCoinsUsecases,
            CoinsMapper coinsMapper, UpdateCoinsUsecases updateCoinsUsecases, DeleteCoinsUsecases deleteCoinsUsecases,
            CollectEmailForTokenService collectEmailForTokenService, FindUsersUsecases findUsersUsecases) {

        this.updateCoinsUsecases = updateCoinsUsecases;
        this.deleteCoinsUsecases = deleteCoinsUsecases;
        this.createCoinsUsecases = createCoinsUsecases;
        this.findCoinsUsecases = findCoinsUsecases;
        this.coinsMapper = coinsMapper;
        this.findUsersUsecases = findUsersUsecases;
        this.collectEmailForTokenService = collectEmailForTokenService;
    }

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody CoinsRequest request,
            HttpServletRequest servletRequest) {
        try {
            Coins coins = coinsMapper.toCoins(request);
            coins.setUser(findUsersUsecases.execute(collectEmailForTokenService.execute(servletRequest)));
            Coins createdCoins = createCoinsUsecases.execute(coins);
            CoinsEntity response = coinsMapper.toCoinsEntity(createdCoins);

            return new ResponseEntity<CoinsEntity>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<ExceptionBody>(new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value()),
                    HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/find/name/{name}")
    ResponseEntity<?> findByName(@PathVariable String name,
            HttpServletRequest servletRequest) {
        try {
            List<Coins> coins = findCoinsUsecases.execute(name, TypeCoinSearch.NAME, findUsersUsecases.execute(collectEmailForTokenService.execute(servletRequest)));
            List<CoinsEntity> response = coinsMapper.toResponse(coins);
            return new ResponseEntity<List<CoinsEntity>>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ExceptionBody>(new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find/symbol/{symbol}")
    ResponseEntity<?> findBySymbol(@PathVariable String symbol,
            HttpServletRequest servletRequest) {
        try {
            List<Coins> coins = findCoinsUsecases.execute(symbol, TypeCoinSearch.SYMBOL, findUsersUsecases.execute(collectEmailForTokenService.execute(servletRequest)));
            List<CoinsEntity> response = coinsMapper.toResponse(coins);
            return new ResponseEntity<List<CoinsEntity>>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ExceptionBody>(new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find/{id}")
    ResponseEntity<?> findUser(@PathVariable Long id) {
        try {
            Coins coins = findCoinsUsecases.execute(id);
            CoinsEntity response = coinsMapper.toCoinsEntity(coins);
            return new ResponseEntity<CoinsEntity>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ExceptionBody>(new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    ResponseEntity<?> findAll(
            HttpServletRequest servletRequest) {
        try {
            List<Coins> Coins = findCoinsUsecases.execute(findUsersUsecases.execute(collectEmailForTokenService.execute(servletRequest)));
            List<CoinsEntity> response = coinsMapper.toResponse(Coins);
            return new ResponseEntity<List<CoinsEntity>>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ExceptionBody>(new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            deleteCoinsUsecases.execute(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ExceptionBody>(new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    ResponseEntity<?> update(@RequestBody CoinsRequest request, @PathVariable Long id,
            HttpServletRequest servletRequest) {
        try {
            Coins coins = coinsMapper.toCoins(request);
            coins.setUser(findUsersUsecases.execute(collectEmailForTokenService.execute(servletRequest)));
            Coins updatedCoins = updateCoinsUsecases.execute(id, coins);
            CoinsEntity response = coinsMapper.toCoinsEntity(updatedCoins);
            return new ResponseEntity<CoinsEntity>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ExceptionBody>(new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value()),
                    HttpStatus.BAD_REQUEST);
        }
    }

}
