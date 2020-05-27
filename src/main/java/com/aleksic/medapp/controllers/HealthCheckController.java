package com.aleksic.medapp.controllers;

import com.aleksic.medapp.models.Doctor;
import com.aleksic.medapp.models.HealthCheck;
import com.aleksic.medapp.services.DoctorsService;
import com.aleksic.medapp.services.HealthCheckService;
import com.aleksic.medapp.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class HealthCheckController {
    @Autowired
    private HealthCheckService healthCheckService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private DoctorsService doctorsService;

    @GetMapping("/healthchecks")
    public ResponseEntity<Map<String, Object>> getAllHealthChecks(@RequestParam(defaultValue = "0") Integer pageNo,
                                                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                                                  @RequestParam(defaultValue = "createdAt") String sortBy) {
        System.out.println("getAllHealthChecks");

        Map<String, Object> allHealthChecks = healthCheckService.getAllHealthChecks(pageNo, pageSize, sortBy);
        return new ResponseEntity<Map<String, Object>>(allHealthChecks, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/user/healthchecks/{healthCheckId}")
    public HealthCheck getHealthCheck(@PathVariable Integer healthCheckId) {
        System.out.println("getHealthCheck");

        return healthCheckService.getCheck(healthCheckId);
    }

    @GetMapping("/healthchecks/{specialization}")
    public Iterable<HealthCheck> getAllHealthChecksBySpecialization(@PathVariable String specialization) {
        System.out.println("getAllHealthChecksBySpecialization");
        return healthCheckService.getHealthChecksBySpecializationName(specialization);
    }


    @GetMapping(value = "/healthchecks/search", params = "fullName")
    public ResponseEntity<Map<String, Object>> searchHealthchecksByDoctorFullName(@RequestParam String fullName,
                                                                                  @RequestParam(defaultValue = "0") Integer pageNo,
                                                                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                                                                  @RequestParam(defaultValue = "createdAt") String sortBy) {
        System.out.println("searchHealthchecksByDoctorFullName");

        Map<String, Object> allHealthChecks = healthCheckService.getAllHealthChecksBtyDoctorFullName(fullName, pageNo, pageSize, sortBy);
        return new ResponseEntity<Map<String, Object>>(allHealthChecks, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/healthchecks/search", params = "specialization")
    public ResponseEntity<Map<String, Object>> searchHealthchecksByDoctorSpecialization(@RequestParam String specialization,
                                                                                        @RequestParam(defaultValue = "0") Integer pageNo,
                                                                                        @RequestParam(defaultValue = "10") Integer pageSize,
                                                                                        @RequestParam(defaultValue = "createdAt") String sortBy) {
        System.out.println("searchHealthchecksByDoctorSpecialization");

        Map<String, Object> allHealthChecks = healthCheckService.getAllHealthChecksByDoctorSpecialization(specialization, pageNo, pageSize, sortBy);
        return new ResponseEntity<Map<String, Object>>(allHealthChecks, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/healthchecks")
    public ResponseEntity<?> addHealthCheck(@Valid @RequestBody HealthCheck check, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, Object> errorMap = new HashMap<>();
            List<FieldError> errors = result.getFieldErrors();
            List<String> errorMessages = new ArrayList<>();
            for (FieldError msg : errors) {
                errorMessages.add(msg.getField() + " " + msg.getDefaultMessage());
            }

            errorMap.put("resultErrors", errorMessages);
            return new ResponseEntity(errorMap, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        Doctor newDoctor = doctorsService.handleDoctorsRatings(check.getDoctor());
        check.setDoctor(newDoctor);
        HealthCheck newCheck = healthCheckService.addHealthCheck(check);
        return new ResponseEntity(newCheck, HttpStatus.CREATED);
    }

    @PutMapping(path = "/healthchecks/update")
    public ResponseEntity<HealthCheck> updateHealthCheck(@RequestBody HealthCheck hc) {
        HealthCheck newHealthCheck = healthCheckService.addHealthCheck(hc);
        return new ResponseEntity(newHealthCheck, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/healthchecks/{id}")
    public ResponseEntity<?> deleteHealthcheck(@PathVariable Integer id) {
        healthCheckService.deleteHealthcheck(id);
        return new ResponseEntity(new HttpHeaders(), HttpStatus.OK);
    }
}
