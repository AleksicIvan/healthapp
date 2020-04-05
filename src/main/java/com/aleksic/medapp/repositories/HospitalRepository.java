package com.aleksic.medapp.repositories;

import com.aleksic.medapp.models.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
}
