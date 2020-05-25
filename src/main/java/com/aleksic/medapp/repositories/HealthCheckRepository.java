package com.aleksic.medapp.repositories;

import com.aleksic.medapp.models.HealthCheck;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface HealthCheckRepository extends JpaRepository<HealthCheck, Integer> {
    Page<HealthCheck> findAll(Pageable pageable);
    public Page<HealthCheck> findHealthChecksByUserId(Integer id, Pageable pageable);
    public HealthCheck findHealthCheckById(Integer healthCheckId);
    public List<HealthCheck> findAllByDoctorSpecializationName(String name);
    public Page<HealthCheck> findHealthCheckByDoctorFullNameContainingIgnoreCase(String fullName, Pageable pageable);
    public Page<HealthCheck> findHealthCheckByDoctorSpecializationNameContainingIgnoreCase(String specialization, Pageable pageable);
}
