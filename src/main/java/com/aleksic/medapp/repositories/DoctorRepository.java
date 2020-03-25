package com.aleksic.medapp.repositories;

import com.aleksic.medapp.models.Doctor;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<Doctor, Integer> {
}
