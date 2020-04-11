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

    public List<Hospital> getHospitalByAddressCity (String city) {
        return hospitalRepository.findHospitalByAddressCityContainingIgnoreCase(city);
    }

    public List<Hospital> getHospitalByName (String name) {
        return hospitalRepository.findHospitalByNameContainingIgnoreCase(name);
    }

    public List<Hospital> getHospitalByAddressStreet (String street) {
        return hospitalRepository.findHospitalByAddressStreetContainingIgnoreCase(street);
    }

    public Hospital editHospital (Hospital hospital) {
        try {
             return hospitalRepository.save(hospital);
        } catch (Exception e) {
            throw new GeneralException("Something went wrong while editing health check.");
        }
    }

    public Hospital addHospital (Hospital hospital) {
        try {
            return hospitalRepository.save(hospital);
        } catch (Exception e) {
            throw new GeneralException("Something went wrong while saving new health check.");
        }
    }

    public boolean findHospitalById (int id) {
        try {
            Hospital foundHospital = hospitalRepository.findById(id).get();
            if (foundHospital != null) return true;
            else return false;
        } catch (Exception e) {
            throw new GeneralException("Something went wrong while saving new health check.");
        }
    }


}

