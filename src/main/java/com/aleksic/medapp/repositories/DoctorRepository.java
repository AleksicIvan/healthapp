package com.aleksic.medapp.repositories;

import com.aleksic.medapp.models.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Page<Doctor> findAll(Pageable pageable);

    Page<Doctor> findDoctorsByFullNameContainingIgnoreCase(String fullName, Pageable pageable);

    Page<Doctor> findDoctorsBySpecializationNameContainingIgnoreCase(String specialization, Pageable pageable);

    List<Doctor> findDoctorsBySpecializationNameContainingIgnoreCase(String specialization);

    List<Doctor> findDoctorsByFullNameContainingIgnoreCase(String fullName);

    List<Doctor> findDoctorsByFirstNameContainingIgnoreCase(String firstName);

    List<Doctor> findDoctorsByLastNameContainingIgnoreCase(String lastName);
}
