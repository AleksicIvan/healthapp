package com.aleksic.medapp.repositories;

import com.aleksic.medapp.models.Specialization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {
    List<Specialization> findAll();

    Page<Specialization> findAll(Pageable pageable);

    Page<Specialization> findSpecializationByNameContainingIgnoreCase(String name, Pageable pageable);
}

