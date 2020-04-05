package com.aleksic.medapp.controllers;

import com.aleksic.medapp.models.Doctor;
import com.aleksic.medapp.models.Hospital;
import com.aleksic.medapp.services.HospitalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api")
public class HospitalController {
    @Autowired
    HospitalsService hospitalsService;

    @GetMapping("hospitals")
    public Iterable<Hospital> getAllHospitals () {
        return hospitalsService.getAllHospitals();
    }

    @PostMapping("hospitals")
    public Hospital addHospital (@RequestBody Hospital hospital) {
        return hospitalsService.addHospital(hospital);
    }

    @PutMapping("hospitals/{id}")
    public Hospital updateHospital (@Valid @RequestBody Hospital newHospital, @PathVariable Integer id) {
        return hospitalsService.editHospital(newHospital);
    }
}
