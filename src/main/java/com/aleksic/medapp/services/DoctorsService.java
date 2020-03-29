package com.aleksic.medapp.services;

import com.aleksic.medapp.models.Doctor;
import com.aleksic.medapp.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DoctorsService {
    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors () {
        return doctorRepository.findAll();
    }

    public Doctor addDoctor (Doctor doc) {
        return doctorRepository.save(doc);
    }

    public Doctor updateDoctor (Doctor doc, Integer id) {
        return doctorRepository
                .findById(id)
                .map(doctor -> {
                    doctor.setFirstName(doc.getFirstName());
                    doctor.setLastName(doc.getLastName());
                    doctor.setSpecialization(doc.getSpecialization());
                    return doctorRepository.save(doctor);
                })
                .orElseGet(() -> {
                    doc.setId(id);
                    return doctorRepository.save(doc);
                });
    }

    public void deleteDoctor (Integer id) {
        doctorRepository.deleteById(id);
    }


}
