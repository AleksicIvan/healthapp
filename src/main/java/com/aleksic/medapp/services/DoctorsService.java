package com.aleksic.medapp.services;

import com.aleksic.medapp.models.Doctor;
import com.aleksic.medapp.models.HealthCheck;
import com.aleksic.medapp.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DoctorsService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PaginationService paginationService;

    public Map<String, Object> getAllDoctors (Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
        Page<Doctor> pagedResult = doctorRepository.findAll(paging);

        return paginationService.getPagedEntites(pagedResult);
    }


    public List<Doctor> getDoctorsByFirstName (String firstName) {
        return doctorRepository.findDoctorsByFirstNameContainingIgnoreCase (firstName);
    }

    public List<Doctor> getDoctorsByLastName (String lastName) {
        return doctorRepository.findDoctorsByLastNameContainingIgnoreCase (lastName);
    }

    public List<Doctor> getDoctorsByFullName (String lastName) {
        return doctorRepository.findDoctorsByFullNameContainingIgnoreCase (lastName);
    }

    public List<Doctor> getDoctorsBySpecialization (String specialization) {
        return doctorRepository.findDoctorsBySpecializationNameContainingIgnoreCase (specialization);
    }

    public Doctor addDoctor (Doctor doc) {
        doc.setFullName(doc.getFirstName() + " " + doc.getLastName());
        return doctorRepository.save(doc);
    }

    private int computeRating (Integer givenRating, Double noOfRatings) {
        Integer calculatedNewRating = (int) Math.abs(givenRating / noOfRatings);
        return calculatedNewRating;
    }

    public Doctor handleDoctorsRatings (Doctor doc) {
        Integer safeAllRatings;
        Double safeNoOfRatings;

        if (doc.getAllRatings() == null) {
            safeAllRatings = 0;
        } else {
            safeAllRatings = doc.getAllRatings();
        }

        if (doc.getNoOfRatings() == null) {
            safeNoOfRatings = 0.0;
        } else {
            safeNoOfRatings = doc.getNoOfRatings();
        }
        System.out.println("safeAllRatings " + safeAllRatings);
        System.out.println("safeNoOfRatings " + safeNoOfRatings);

        Integer newRating = computeRating(safeAllRatings, safeNoOfRatings);
        System.out.println("newRating " + newRating);

        doc.setRating(newRating);
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
