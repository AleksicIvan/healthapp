package com.aleksic.medapp.controllers;

import com.aleksic.medapp.models.Specialization;
import com.aleksic.medapp.services.SpecializationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class SpecializationController {
    @Autowired
    private SpecializationsService specializationsService;

    @GetMapping("/specializations")
    public @ResponseBody
    Iterable<Specialization> getAllSpecializations () {
        return specializationsService.getAllSpecializations();
    }

    @GetMapping("/specializations/{id}")
    public @ResponseBody Specialization getSpecialization (@PathVariable Integer id) throws ResponseStatusException {
        try {
            return specializationsService.getSpecialization(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Specialization not found");
        }
    }

    @PostMapping(path="/specializations")
    public @ResponseBody Specialization addNewSpecialization (@RequestBody Specialization spec) {
        return specializationsService.addSpecialization(spec);
    }

    @PutMapping("/specializations/{id}")
    public Specialization updateSpecialization (@RequestBody Specialization newSpec, @PathVariable Integer id) {
        return specializationsService.updateSpecialization(newSpec, id);
    }

    @DeleteMapping("/specializations/{id}")
    void deleteSpecialization(@PathVariable Integer id) {
        specializationsService.deleteSpecialization(id);
    }
}
