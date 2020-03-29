package com.aleksic.medapp.repositories;

import com.aleksic.medapp.models.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {
}

