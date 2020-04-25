package com.aleksic.medapp.controllers;

import com.aleksic.medapp.models.Hospital;
import com.aleksic.medapp.services.HospitalsService;
import com.aleksic.medapp.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HospitalController {
    @Autowired
    HospitalsService hospitalsService;
    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @GetMapping("hospitals/search")
    public List<Hospital> getAllHospitals () {
        return hospitalsService.getAllHospitals();
    }

    @GetMapping(value = "hospitals/search", params = "city")
    public List<Hospital> getHospitalByCityAddress (@RequestParam String city) {
        return hospitalsService.getHospitalByAddressCity(city);
    }

    @GetMapping(value = "hospitals/search", params = "street")
    public Iterable<Hospital> getHospitalByStreetAddress (@RequestParam String street) {
        return hospitalsService.getHospitalByAddressStreet(street);
    }

    @GetMapping(value = "hospitals/search", params = "name")
    public Iterable<Hospital> getHospitalByName (@RequestParam String name) {
        return hospitalsService.getHospitalByName(name);
    }

    @PostMapping("hospitals")
    public ResponseEntity<?> addHospital (@RequestBody Hospital hospital, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap!=null) return errorMap;

        Hospital newHospital = hospitalsService.addHospital(hospital);
        return new ResponseEntity(newHospital, HttpStatus.OK);
    }

    @PutMapping("hospitals/{id}")
    public ResponseEntity updateHospital (@Valid @RequestBody Hospital newHospital, @PathVariable Integer id, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap!=null) return errorMap;

        Hospital hospital = hospitalsService.editHospital(newHospital);
        return new ResponseEntity(hospital, HttpStatus.OK);
    }
}
