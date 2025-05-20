package com.site.dev.adapter.controllers;

import com.site.dev.adapter.controllers.dto.goals.GoalsRequest;
import com.site.dev.adapter.controllers.dto.goals.GoalsResponce;
import com.site.dev.adapter.mappers.GoalsMapper;
import com.site.dev.adapter.models.ExceptionBody;
import com.site.dev.core.applications.usecases.coins.FindCoinsUsecases;
import com.site.dev.core.applications.usecases.goals.CreateGoalsUsecases;
import com.site.dev.core.applications.usecases.goals.DeleteGoalsUsecases;
import com.site.dev.core.applications.usecases.goals.FindGoalsUsecases;
import com.site.dev.core.applications.usecases.goals.UpdateGoalsUsecases;
import com.site.dev.core.applications.usecases.movements.CreateMovementsUsecases;
import com.site.dev.core.applications.usecases.movements.DeleteMovementsUsecases;
import com.site.dev.core.applications.usecases.movements.FindMovementsUsecases;
import com.site.dev.core.applications.usecases.movements.UpdateMovementsUsecases;
import com.site.dev.core.applications.usecases.users.FindUsersUsecases;
import com.site.dev.core.domain.entity.Goals;
import com.site.dev.core.domain.entity.Movements;
import com.site.dev.services.CollectEmailForTokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/goals")
public class GoalsController {

    private final CreateGoalsUsecases createGoalsUsecases;
    private final FindGoalsUsecases findGoalsUsecases;
    private final UpdateGoalsUsecases updateGoalsUsecases;
    private final DeleteGoalsUsecases deleteGoalsUsecases;
    private FindCoinsUsecases findCoinsUsecases;
    private FindUsersUsecases findUsersUsecases;
    private GoalsMapper goalsMapper;

    private final CollectEmailForTokenService collectEmailForTokenService;

    public GoalsController(CreateGoalsUsecases createGoalsUsecases,
            FindGoalsUsecases findGoalsUsecases, UpdateGoalsUsecases updateGoalsUsecases,
            DeleteGoalsUsecases deleteGoalsUsecases, GoalsMapper goalsMapper,
            FindCoinsUsecases findCoinsUsecases,
            CollectEmailForTokenService collectEmailForTokenService, FindUsersUsecases findUsersUsecases) {
        this.createGoalsUsecases = createGoalsUsecases;
        this.findGoalsUsecases = findGoalsUsecases;
        this.updateGoalsUsecases = updateGoalsUsecases;
        this.findCoinsUsecases = findCoinsUsecases;
        this.deleteGoalsUsecases = deleteGoalsUsecases;
        this.findUsersUsecases = findUsersUsecases;
        this.goalsMapper = goalsMapper;
        this.collectEmailForTokenService = collectEmailForTokenService;
    }


    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody GoalsRequest request, HttpServletRequest servletRequest) {
        try {
            Goals goalsRequest = goalsMapper.toGoals(request);
            goalsRequest.setUser(findUsersUsecases.execute(collectEmailForTokenService.execute(servletRequest)));
            Goals goals = createGoalsUsecases.execute(goalsRequest);
            GoalsResponce goalsResponce = goalsMapper.toResponce(goals);
            return new ResponseEntity<GoalsResponce>(goalsResponce, HttpStatus.CREATED);
        } catch (Exception e) {
            ExceptionBody body = new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<ExceptionBody>(body, HttpStatus.BAD_REQUEST);
        }
    }

    

    @GetMapping("/find/{uuid}")
    ResponseEntity<?> find(@PathVariable UUID uuid) {
        try {
            Goals goals = findGoalsUsecases.execute(uuid);
            GoalsResponce goalsResponce = goalsMapper.toResponce(goals);
            return new ResponseEntity<GoalsResponce>(goalsResponce, HttpStatus.OK);
        } catch (Exception e) {
            ExceptionBody body = new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<ExceptionBody>(body, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    ResponseEntity<?> findAll() {
        try {
            List<Goals> goals = findGoalsUsecases.execute();
            List<GoalsResponce> goalsResponce = goalsMapper.toResponce(goals);
            return new ResponseEntity<List<GoalsResponce>>(goalsResponce, HttpStatus.OK);
        } catch (Exception e) {
            ExceptionBody body = new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<ExceptionBody>(body, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find/coins/{uuid}")
    ResponseEntity<?> findByCoins(@PathVariable UUID uuid) {
        try {
            List<Goals> goals = findGoalsUsecases.execute(findCoinsUsecases.execute(uuid));
            List<GoalsResponce> response = goalsMapper.toResponce(goals);
            return new ResponseEntity<List<GoalsResponce>>(response, HttpStatus.OK);
        } catch (Exception e) {
            ExceptionBody body = new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<ExceptionBody>(body, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{uuid}")
    ResponseEntity<?> update(@RequestBody GoalsRequest request, @PathVariable UUID uuid, HttpServletRequest servletRequest) {
        try {
            Goals goalsRequest = goalsMapper.toGoals(request);
            goalsRequest.setUser(findUsersUsecases.execute(collectEmailForTokenService.execute(servletRequest)));
            Goals goals = updateGoalsUsecases.execute(uuid, goalsRequest);
            GoalsResponce goalsResponce = goalsMapper.toResponce(goals);
            return new ResponseEntity<GoalsResponce>(goalsResponce, HttpStatus.OK);
        } catch (Exception e) {
            ExceptionBody body = new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<ExceptionBody>(body, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{uuid}")
    ResponseEntity<?> delete(@PathVariable UUID uuid) {
        try {
            deleteGoalsUsecases.execute(uuid);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            ExceptionBody body = new ExceptionBody(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<ExceptionBody>(body, HttpStatus.BAD_REQUEST);
        }
    }
}
