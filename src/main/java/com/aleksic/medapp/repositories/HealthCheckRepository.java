package com.aleksic.medapp.repositories;

import com.aleksic.medapp.models.HealthCheck;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HealthCheckRepository extends JpaRepository<HealthCheck, Integer> {
    Page<HealthCheck> findAll(Pageable pageable);

    Page<HealthCheck> findHealthChecksByUserId(Integer id, Pageable pageable);

    Page<HealthCheck> findHealthCheckByUserIdAndDoctorFullNameContainingIgnoreCase(Integer id, String fullName, Pageable pageable);

    Page<HealthCheck> findHealthCheckByUserIdAndDoctorSpecializationNameContainingIgnoreCase(Integer id, String specialization, Pageable pageable);

    HealthCheck findHealthCheckById(Integer healthCheckId);

    List<HealthCheck> findAllByDoctorSpecializationName(String name);

    Page<HealthCheck> findHealthCheckByDoctorFullNameContainingIgnoreCase(String fullName, Pageable pageable);

    Page<HealthCheck> findHealthCheckByDoctorSpecializationNameContainingIgnoreCase(String specialization, Pageable pageable);
}
