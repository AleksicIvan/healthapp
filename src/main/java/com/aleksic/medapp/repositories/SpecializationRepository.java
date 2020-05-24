package com.aleksic.medapp.repositories;

import com.aleksic.medapp.models.Doctor;
import com.aleksic.medapp.models.Specialization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {
    Page<Specialization> findAll(Pageable pageable);
    List<Specialization> findSpecializationByNameContainingIgnoreCase(String name);
}

