package com.aleksic.medapp.services;

import com.aleksic.medapp.exceptions.GeneralException;
import com.aleksic.medapp.models.Doctor;
import com.aleksic.medapp.models.Hospital;
import com.aleksic.medapp.repositories.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Service
public class HospitalsService {
    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private PaginationService paginationService;

    public List<Hospital> getAllHospitals () {
        return hospitalRepository.findAll();
    }

    public Map<String, Object> getAllHospitals (Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        Page<Hospital> pagedResult = hospitalRepository.findAll(paging);

        return paginationService.getPagedEntites(pagedResult);
    }

    public Map<String, Object> getHospitalByAddressCity (String city, Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        Page<Hospital> pagedResult = hospitalRepository.findHospitalByAddressCityContainingIgnoreCase(city, paging);

        return paginationService.getPagedEntites(pagedResult);
    }

    public Map<String, Object> getHospitalByName (String name, Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        Page<Hospital> pagedResult = hospitalRepository.findHospitalByNameContainingIgnoreCase(name, paging);

        return paginationService.getPagedEntites(pagedResult);
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

