package com.aleksic.medapp.controllers;

import com.aleksic.medapp.models.Doctor;
import com.aleksic.medapp.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping("/lekari")
    public @ResponseBody
    Iterable<Doctor> getAllDoctors () {
        return doctorRepository.findAll();
    }

    @PostMapping(path="/lekari")
    public @ResponseBody String addNewUser (@RequestBody Doctor doctor) {
        doctorRepository.save(doctor);
        return "Saved";
    }

    @PutMapping("/lekari/{id}")
    public Doctor updateDoctor (@RequestBody Doctor newDoctor, @PathVariable Integer id) {
        return doctorRepository
                .findById(id)
                .map(doctor -> {
                    doctor.setFirstName(newDoctor.getFirstName());
                    doctor.setLastName(newDoctor.getLastName());
                    doctor.setSpecialization(newDoctor.getSpecialization());
                    return doctorRepository.save(doctor);
                })
                .orElseGet(() -> {
                    newDoctor.setId(id);
                    return doctorRepository.save(newDoctor);
                });
    }

    @DeleteMapping("/lekari/{id}")
    void deleteDoctor(@PathVariable Integer id) {
        doctorRepository.deleteById(id);
    }
}
