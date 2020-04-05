package com.aleksic.medapp.services;

import com.aleksic.medapp.exceptions.GeneralException;
import com.aleksic.medapp.models.Hospital;
import com.aleksic.medapp.repositories.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalsService {
    @Autowired
    private HospitalRepository hospitalRepository;

    public List<Hospital> getAllHospitals () {
        return hospitalRepository.findAll();
    }

    public Hospital editHospital (Hospital hospital) {
        Hospital foundHospital = hospitalRepository.findById(hospital.getId()).get();
        try {
            return hospitalRepository.save(hospital);
        } catch (Exception e) {
            throw new GeneralException("Something went wrong while saving new health check.");
        }
    }

    public Hospital addHospital (Hospital hospital) {
        try {
            return hospitalRepository.save(hospital);
        } catch (Exception e) {
            throw new GeneralException("Something went wrong while saving new health check.");
        }
    }
}

