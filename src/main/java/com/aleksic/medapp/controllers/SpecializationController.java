package com.aleksic.medapp.controllers;

import com.aleksic.medapp.models.Specialization;
import com.aleksic.medapp.repositories.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class SpecializationController {
    @Autowired
    private SpecializationRepository specializationRepository;

    @GetMapping("/specijalizacije")
    public @ResponseBody
    Iterable<Specialization> getAllSpecializations () {
        return specializationRepository.findAll();
    }

    @PostMapping(path="/specijalizacije")
    public @ResponseBody String addNewUser (@RequestBody Specialization spec) {
        specializationRepository.save(spec);
        return "Saved";
    }

    @PutMapping("/specijalizacije/{id}")
    public Specialization updateSpecialization (@RequestBody Specialization newSpec, @PathVariable Integer id) {
        return specializationRepository
                .findById(id)
                .map(spec -> {
                    spec.setName(spec.getName());
                    return specializationRepository.save(spec);
                })
                .orElseGet(() -> {
                    newSpec.setId(id);
                    return specializationRepository.save(newSpec);
                });
    }

    @DeleteMapping("/specijalizacije/{id}")
    void deleteSpecialization(@PathVariable Integer id) {
        specializationRepository.deleteById(id);
    }
}
