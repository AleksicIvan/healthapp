package com.aleksic.medapp.services;

import com.aleksic.medapp.models.Doctor;
import com.aleksic.medapp.models.HealthCheck;
import com.aleksic.medapp.models.Specialization;
import com.aleksic.medapp.repositories.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SpecializationsService {
    @Autowired
    private SpecializationRepository specializationRepository;
    @Autowired
    private PaginationService paginationService;

    public Map<String, Object> getAllSpecializations (Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
        Page<Specialization> pagedResult = specializationRepository.findAll(paging);

        return paginationService.getPagedEntites(pagedResult);
    }

    public List<Specialization> getSpecializationByName (String name) {
        return specializationRepository.findSpecializationByNameContainingIgnoreCase(name);
    }

    public Specialization getSpecialization (Integer id) throws Exception {
        Optional<Specialization> spec = specializationRepository.findById(id);
        if (spec.isPresent()) {
            return spec.get();
        }
        throw new Exception("Specialization not found");
    }

    public Specialization addSpecialization (Specialization spec) {
        return specializationRepository.save(spec);
    }

    public Specialization updateSpecialization (Specialization spec, Integer id) {
        return specializationRepository
                .findById(id)
                .map(specialization -> {
                    specialization.setName(spec.getName());
                    return specializationRepository.save(spec);
                })
                .orElseGet(() -> {
                    spec.setId(id);
                    return specializationRepository.save(spec);
                });
    }

    public void deleteSpecialization (Integer id) {
        specializationRepository.deleteById(id);
    }


}
