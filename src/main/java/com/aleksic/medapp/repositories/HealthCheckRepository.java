package com.aleksic.medapp.repositories;

import com.aleksic.medapp.models.HealthCheck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HealthCheckRepository extends JpaRepository<HealthCheck, Integer> {
    public List<HealthCheck> findHealthChecksByUserId(Integer id);
    public List<HealthCheck> findAllByDoctorSpecializationName(String name);
}
