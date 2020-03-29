package com.aleksic.medapp.controllers;

import com.aleksic.medapp.models.Doctor;
import com.aleksic.medapp.services.DoctorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class DoctorController {
    @Autowired
    private DoctorsService doctorsService;

    @GetMapping("/doctors")
    public @ResponseBody
    Iterable<Doctor> getAllDoctors () {
        return doctorsService.getAllDoctors();
    }

    @PostMapping(path="/doctors")
    public @ResponseBody Doctor addNewUser (@RequestBody Doctor doctor) {
        return doctorsService.addDoctor(doctor);
    }

    @PutMapping("/doctors/{id}")
    public Doctor updateDoctor (@RequestBody Doctor newDoctor, @PathVariable Integer id) {
        return doctorsService.updateDoctor(newDoctor, id);
    }

    @DeleteMapping("/doctors/{id}")
    void deleteDoctor(@PathVariable Integer id) {
        doctorsService.deleteDoctor(id);
    }
}
