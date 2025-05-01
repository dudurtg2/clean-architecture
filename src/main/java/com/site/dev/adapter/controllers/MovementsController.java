package com.site.dev.adapter.controllers;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.site.dev.adapter.controllers.DTO.movements.CreateMovementsRequest;
import com.site.dev.adapter.controllers.DTO.movements.CreateMovementsResponse;
import com.site.dev.adapter.entity.ExceptionBody;
import com.site.dev.adapter.mappers.movements.MovementsMapper;
import com.site.dev.core.applications.usecases.movements.CreateMovementsUsecases;
import com.site.dev.core.applications.usecases.movements.FindMovementsUsecases;
import com.site.dev.core.domain.entity.Movements;

@RestController
@RequestMapping("/api/movements")
public class MovementsController {

    private final CreateMovementsUsecases createMovementUsecases;
    private final FindMovementsUsecases findMovementUsecases;
    private final MovementsMapper movementsMapper;

 
    
    public MovementsController(CreateMovementsUsecases createMovementUsecases, FindMovementsUsecases findMovementUsecases, MovementsMapper movementsMapper) {
        this.createMovementUsecases = createMovementUsecases;
        this.findMovementUsecases = findMovementUsecases;
        this.movementsMapper = movementsMapper;
    }

    @PostMapping("/create")
    ResponseEntity<?> createUser(@RequestBody CreateMovementsRequest request) {
        try {
            Movements movements = movementsMapper.toMovements(request);
            Movements movementsCreated = createMovementUsecases.execute(movements);
            CreateMovementsResponse response = movementsMapper.toResponse(movementsCreated);
            return new ResponseEntity<CreateMovementsResponse>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            ExceptionBody body = new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<ExceptionBody>(body, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find/{id}")
    ResponseEntity<?> findUser(@PathVariable Long id) {
        try {
            Movements movements = findMovementUsecases.execute(id);
            CreateMovementsResponse response = movementsMapper.toResponse(movements);
            return new ResponseEntity<CreateMovementsResponse>(response, HttpStatus.OK);
        } catch (Exception e) {
            ExceptionBody body = new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<ExceptionBody>(body, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    ResponseEntity<?> findAll() {
        try {
            List<Movements> movements = findMovementUsecases.execute();
            List<CreateMovementsResponse> response = movementsMapper.toResponseEntity(movements);
            return new ResponseEntity<List<CreateMovementsResponse>>(response, HttpStatus.OK);
        } catch (Exception e) {
            ExceptionBody body = new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<ExceptionBody>(body, HttpStatus.BAD_REQUEST);
        }
    }


}
