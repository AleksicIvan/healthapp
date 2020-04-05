package com.aleksic.medapp.repositories;

import com.aleksic.medapp.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    public List<Doctor> findDoctorsByFirstName(String firstName);
}
