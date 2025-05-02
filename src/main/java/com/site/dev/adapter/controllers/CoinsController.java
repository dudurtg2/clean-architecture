package com.site.dev.adapter.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.site.dev.adapter.controllers.DTO.coins.CreateCoinsRequest;
import com.site.dev.adapter.entity.CoinsEntity;
import com.site.dev.adapter.entity.ExceptionBody;
import com.site.dev.adapter.mappers.coins.CoinsMapper;
import com.site.dev.core.applications.usecases.coins.CreateCoinsUsecases;
import com.site.dev.core.applications.usecases.coins.FindCoinsUsecases;
import com.site.dev.core.domain.entity.Coins;

@RestController
@RequestMapping("/api/coins")
public class CoinsController {

    private final CreateCoinsUsecases createCoinsUsecases;
    private final FindCoinsUsecases findCoinsUsecases;
    private final CoinsMapper coinsMapper;

    @Autowired
    public CoinsController(CreateCoinsUsecases createCoinsUsecases, FindCoinsUsecases findCoinsUsecases, CoinsMapper coinsMapper) {
        this.createCoinsUsecases = createCoinsUsecases;
        this.findCoinsUsecases = findCoinsUsecases;
        this.coinsMapper = coinsMapper;
    }

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody CreateCoinsRequest request) {
        try {
            Coins coins = coinsMapper.toCoins(request);
            Coins createdCoins = createCoinsUsecases.execute(coins);
            CoinsEntity response = coinsMapper.toCoinsEntity(createdCoins);
          
            return new ResponseEntity<CoinsEntity>(response, HttpStatus.CREATED);
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
    ResponseEntity<?> findAll() {
        try {
            List<Coins> Coins = findCoinsUsecases.execute();
            List<CoinsEntity> response = coinsMapper.toResponse(Coins);
            return new ResponseEntity<List<CoinsEntity>>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ExceptionBody>(new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value()),
                    HttpStatus.BAD_REQUEST);
        }
    }

}
