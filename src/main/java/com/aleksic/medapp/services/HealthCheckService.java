package com.aleksic.medapp.services;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.aleksic.medapp.exceptions.GeneralException;
import com.aleksic.medapp.models.HealthCheck;
import com.aleksic.medapp.repositories.HealthCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HealthCheckService {
    @Autowired
    private HealthCheckRepository healthCheckRepository;

    public List<HealthCheck> getAllHealthChecks () {
        return healthCheckRepository.findAll();
    }

    public HealthCheck getCheck (Integer healthCheckId) {
        try {
            return healthCheckRepository.findHealthCheckById(healthCheckId);
        } catch (Exception e) {
            throw new GeneralException("Something went wrong while getting health check.");
        }
    }
    public HealthCheck addHealthCheck (HealthCheck check) {
        try {
            return healthCheckRepository.save(check);
        } catch (Exception e) {
            throw new GeneralException("Something went wrong while saving new health check.");
        }
    }

    public List<HealthCheck> getHealthChecksByUserId (Integer id) {
        List<HealthCheck> usersChecks = new ArrayList<HealthCheck>();
        healthCheckRepository.findHealthChecksByUserId(id)
        .forEach(usersChecks::add);
        return usersChecks;
    }

    public List<HealthCheck> getHealthChecksBySpecializationName (String name) {
        List<HealthCheck> healthChecks = new ArrayList<HealthCheck>();
        healthCheckRepository.findAllByDoctorSpecializationName(name)
                .forEach(healthChecks::add);
        return healthChecks;
    }

    public Map<String, Object> convertHealthcheckToMap (HealthCheck check) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("id", check.getId());
        stringObjectHashMap.put("createdAt", check.getCreatedAt());
        stringObjectHashMap.put("hospital", check.getHospital());
        stringObjectHashMap.put("doctor", check.getDoctor());
        stringObjectHashMap.put("description", check.getDescription());
        return stringObjectHashMap;
    }
}
