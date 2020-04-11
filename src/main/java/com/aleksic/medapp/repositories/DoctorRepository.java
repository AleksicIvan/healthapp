package com.aleksic.medapp.repositories;

import com.aleksic.medapp.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    List<Doctor> findDoctorsByFirstNameContainingIgnoreCase(String firstName);
    List<Doctor> findDoctorsByLastNameContainingIgnoreCase(String lastName);
    List<Doctor> findDoctorsBySpecializationNameContainingIgnoreCase(String specialization);
}
