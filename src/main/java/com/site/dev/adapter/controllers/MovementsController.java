package com.site.dev.adapter.controllers;

import java.util.List;
import java.util.UUID;

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

import com.site.dev.adapter.controllers.dto.movements.MovementsRequest;
import com.site.dev.adapter.controllers.dto.movements.MovementsResponse;
import com.site.dev.adapter.mappers.MovementsMapper;
import com.site.dev.adapter.models.ExceptionBody;
import com.site.dev.core.applications.usecases.coins.FindCoinsUsecases;
import com.site.dev.core.applications.usecases.movements.CreateMovementsUsecases;
import com.site.dev.core.applications.usecases.movements.DeleteMovementsUsecases;
import com.site.dev.core.applications.usecases.movements.FindMovementsUsecases;
import com.site.dev.core.applications.usecases.movements.UpdateMovementsUsecases;
import com.site.dev.core.domain.entity.Movements;
import com.site.dev.services.CollectEmailForTokenService;

@RestController
@RequestMapping("/api/movements")
public class MovementsController {

    private final CreateMovementsUsecases createMovementUsecases;
    private final FindMovementsUsecases findMovementUsecases;
    private final FindCoinsUsecases findCoinsUsecases;
    private final DeleteMovementsUsecases deleteMovementUsecases;
    private final UpdateMovementsUsecases updateMovementUsecases;
    private final MovementsMapper movementsMapper;
    private final CollectEmailForTokenService collectEmailForTokenService;
    

    @Autowired
    public MovementsController(CreateMovementsUsecases createMovementUsecases,
            FindMovementsUsecases findMovementUsecases, MovementsMapper movementsMapper,
            FindCoinsUsecases findCoinsUsecases, CollectEmailForTokenService collectEmailForTokenService,
            DeleteMovementsUsecases deleteMovementsUsecases, UpdateMovementsUsecases updateMovementsUsecases) {
        this.createMovementUsecases = createMovementUsecases;
        this.findMovementUsecases = findMovementUsecases;
        this.movementsMapper = movementsMapper;
        this.findCoinsUsecases = findCoinsUsecases;
        this.collectEmailForTokenService = collectEmailForTokenService;
        this.deleteMovementUsecases = deleteMovementsUsecases;
        this.updateMovementUsecases = updateMovementsUsecases;

    }

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody MovementsRequest request) {
        try {
            
            Movements movements = movementsMapper.toMovements(request);
            Movements movementsCreated = createMovementUsecases.execute(movements);
            MovementsResponse response = movementsMapper.toResponse(movementsCreated);
            return new ResponseEntity<MovementsResponse>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            ExceptionBody body = new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<ExceptionBody>(body, HttpStatus.BAD_REQUEST);
        }
    }

    

    @GetMapping("/find/{uuid}")
    ResponseEntity<?> find(@PathVariable UUID uuid) {
        try {
            Movements movements = findMovementUsecases.execute(uuid);
            MovementsResponse response = movementsMapper.toResponse(movements);
            return new ResponseEntity<MovementsResponse>(response, HttpStatus.OK);
        } catch (Exception e) {
            ExceptionBody body = new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<ExceptionBody>(body, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    ResponseEntity<?> findAll() {
        try {
            List<Movements> movements = findMovementUsecases.execute();
            List<MovementsResponse> response = movementsMapper.toResponseEntity(movements);
            return new ResponseEntity<List<MovementsResponse>>(response, HttpStatus.OK);
        } catch (Exception e) {
            ExceptionBody body = new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<ExceptionBody>(body, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find/coins/{uuid}")
    ResponseEntity<?> findByCoins(@PathVariable UUID uuid) {
        try {
            List<Movements> movements = findMovementUsecases.execute(findCoinsUsecases.execute(uuid));
            List<MovementsResponse> response = movementsMapper.toResponseEntity(movements);
            return new ResponseEntity<List<MovementsResponse>>(response, HttpStatus.OK);
        } catch (Exception e) {
            ExceptionBody body = new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<ExceptionBody>(body, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{uuid}")
    ResponseEntity<?> update(@RequestBody MovementsRequest request, @PathVariable UUID uuid) {
        try {
            Movements movements = movementsMapper.toMovements(request);
            Movements updatedMovements = updateMovementUsecases.execute(uuid, movements);
            MovementsResponse response = movementsMapper.toResponse(updatedMovements);
            return new ResponseEntity<MovementsResponse>(response, HttpStatus.OK);
        } catch (Exception e) {
            ExceptionBody body = new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<ExceptionBody>(body, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{uuid}")
    ResponseEntity<?> delete(@PathVariable UUID uuid) {
        try {
            deleteMovementUsecases.execute(uuid);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            ExceptionBody body = new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<ExceptionBody>(body, HttpStatus.BAD_REQUEST);
        }
    }
}
