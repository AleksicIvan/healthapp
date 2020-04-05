package com.aleksic.medapp.controllers;

import com.aleksic.medapp.models.HealthCheck;
import com.aleksic.medapp.services.HealthCheckService;
import com.aleksic.medapp.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class HealthCheckController {
    @Autowired
    private HealthCheckService healthCheckService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @GetMapping("/healthchecks")
    public Iterable<HealthCheck> getAllHealthChecks () {
        return healthCheckService.getAllHealthChecks();
    }

    @GetMapping("/healthchecks/{specialization}")
    public Iterable<HealthCheck> getAllHealthChecksBySpecialization (@PathVariable String specialization) {
        return healthCheckService.getHealthChecksBySpecializationName(specialization);
    }

    @PostMapping("/healthchecks")
    public ResponseEntity<?> addHealthCheck (@Valid @RequestBody HealthCheck check, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        HealthCheck newCheck = healthCheckService.addHealthCheck(check);
        return new ResponseEntity(newCheck, HttpStatus.CREATED);
    }
}
