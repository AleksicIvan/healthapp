package com.aleksic.medapp.services;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.aleksic.medapp.exceptions.GeneralException;
import com.aleksic.medapp.models.HealthCheck;
import com.aleksic.medapp.models.Report;
import com.aleksic.medapp.repositories.HealthCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HealthCheckService {
    @Autowired
    private HealthCheckRepository healthCheckRepository;

    private Map<String, Object> getPagedChecks(Page<HealthCheck> pagedResult) {
        HashMap<String, Object> defaultMeta = new HashMap<>();

        if(pagedResult.hasContent()) {
            HashMap<String, Object> responseWithMeta = new HashMap<>();
            responseWithMeta.put("data", pagedResult.getContent());
            responseWithMeta.put("meta", convertHealthcheckToMap(pagedResult));

            return responseWithMeta;
        } else {
            System.out.println("I do not have result");
            HashMap<String, Object> responseWithMeta = new HashMap<>();
            defaultMeta.put("count", 1);
            defaultMeta.put("page", 1);
            defaultMeta.put("noPages", 1);
            responseWithMeta.put("data", new ArrayList<HealthCheck>());
            responseWithMeta.put("meta", defaultMeta);

            return responseWithMeta;
        }
    }

    public Map<String, Object> getHealthChecksByUserId (Integer id, Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        Page<HealthCheck> pagedResult = healthCheckRepository.findHealthChecksByUserId(id, paging);

        return getPagedChecks(pagedResult);

//        List<HealthCheck> usersChecks = new ArrayList<HealthCheck>();
//        healthCheckRepository.findHealthChecksByUserId(id)
//                .forEach(usersChecks::add);
//        return usersChecks;
    }

    public Map<String, Object> getAllHealthChecks (Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        Page<HealthCheck> pagedResult = healthCheckRepository.findAll(paging);

        return getPagedChecks(pagedResult);
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

    public List<HealthCheck> getHealthChecksBySpecializationName (String name) {
        List<HealthCheck> healthChecks = new ArrayList<HealthCheck>();
        healthCheckRepository.findAllByDoctorSpecializationName(name)
                .forEach(healthChecks::add);
        return healthChecks;
    }

    public Map<String, Object> converPaginatedResultToMap (HealthCheck check) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("id", check.getId());
        stringObjectHashMap.put("createdAt", check.getCreatedAt());
        stringObjectHashMap.put("hospital", check.getHospital());
        stringObjectHashMap.put("doctor", check.getDoctor());
        stringObjectHashMap.put("description", check.getDescription());
        return stringObjectHashMap;
    }

    public Map<String, Object> convertHealthcheckToMap (Page page) {
        long count = page.getTotalElements();
        int pageForMeta = page.getNumber() + 1;
        double countDivided = (double) count / page.getSize();
        int noPages = (int)Math.ceil(countDivided);

        HashMap<String, Object> newResponseMeta = new HashMap<>();
        newResponseMeta.put("count", count);
        newResponseMeta.put("page",pageForMeta);
        newResponseMeta.put("noPages", noPages);

        return newResponseMeta;
    }
}
