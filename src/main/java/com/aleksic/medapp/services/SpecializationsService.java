package com.aleksic.medapp.services;

import com.aleksic.medapp.models.Doctor;
import com.aleksic.medapp.models.Specialization;
import com.aleksic.medapp.repositories.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SpecializationsService {
    @Autowired
    private SpecializationRepository specializationRepository;

    public List<Specialization> getAllSpecializations () {
        return specializationRepository.findAll();
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
