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

import com.site.dev.adapter.controllers.DTO.movements.MovementsRequest;
import com.site.dev.adapter.controllers.DTO.movements.MovementsResponse;
import com.site.dev.adapter.entity.ExceptionBody;
import com.site.dev.adapter.mappers.MovementsMapper;
import com.site.dev.core.applications.usecases.movements.CreateMovementsUsecases;
import com.site.dev.core.applications.usecases.movements.FindMovementsUsecases;
import com.site.dev.core.domain.entity.Movements;

@RestController
@RequestMapping("/api/movements")
public class MovementsController {

    private final CreateMovementsUsecases createMovementUsecases;
    private final FindMovementsUsecases findMovementUsecases;
    private final MovementsMapper movementsMapper;

 
    @Autowired
    public MovementsController(CreateMovementsUsecases createMovementUsecases, FindMovementsUsecases findMovementUsecases, MovementsMapper movementsMapper) {
        this.createMovementUsecases = createMovementUsecases;
        this.findMovementUsecases = findMovementUsecases;
        this.movementsMapper = movementsMapper;
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

    @GetMapping("/find/{id}")
    ResponseEntity<?> find(@PathVariable Long id) {
        try {
            Movements movements = findMovementUsecases.execute(id);
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


}
