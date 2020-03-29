package com.aleksic.medapp.repositories;

import com.aleksic.medapp.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
