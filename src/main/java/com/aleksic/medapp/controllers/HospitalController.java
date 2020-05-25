package com.aleksic.medapp.controllers;

import com.aleksic.medapp.models.Hospital;
import com.aleksic.medapp.services.HospitalsService;
import com.aleksic.medapp.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HospitalController {
    @Autowired
    HospitalsService hospitalsService;
    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @GetMapping("/hospitals/all")
    public ResponseEntity<List> getAllHospitalsWithoutPagination () {
        List<Hospital> hospitals = hospitalsService.getAllHospitals();
        return new ResponseEntity(hospitals, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/hospitals")
    public ResponseEntity<Map<String, Object>> getAllHospitals (@RequestParam(defaultValue = "0") Integer pageNo,
                                                              @RequestParam(defaultValue = "10") Integer pageSize,
                                                              @RequestParam(defaultValue = "id") String sortBy) {

        Map<String, Object> allHospitals = hospitalsService.getAllHospitals(pageNo, pageSize, sortBy);
        return new ResponseEntity<Map<String, Object>>(allHospitals, new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping(value = "hospitals/search", params = "city")
    public ResponseEntity<Map<String, Object>> getHospitalByCityAddress (@RequestParam String city,
                                                    @RequestParam(defaultValue = "0") Integer pageNo,
                                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                                    @RequestParam(defaultValue = "id") String sortBy) {
        Map<String, Object> allHospitals = hospitalsService.getHospitalByAddressCity(city, pageNo, pageSize, sortBy);
        return new ResponseEntity<Map<String, Object>>(allHospitals, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "hospitals/search", params = "street")
    public Iterable<Hospital> getHospitalByStreetAddress (@RequestParam String street) {
        return hospitalsService.getHospitalByAddressStreet(street);
    }

    @GetMapping(value = "hospitals/search", params = "name")
    public ResponseEntity<Map<String, Object>> getHospitalByName (@RequestParam String name,
                                                                         @RequestParam(defaultValue = "0") Integer pageNo,
                                                                         @RequestParam(defaultValue = "10") Integer pageSize,
                                                                         @RequestParam(defaultValue = "id") String sortBy) {
        Map<String, Object> allHospitals = hospitalsService.getHospitalByName(name, pageNo, pageSize, sortBy);
        return new ResponseEntity<Map<String, Object>>(allHospitals, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("hospitals")
    public ResponseEntity<?> addHospital (@RequestBody Hospital hospital, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap!=null) return errorMap;

        Hospital newHospital = hospitalsService.addHospital(hospital);
        return new ResponseEntity(newHospital, new HttpHeaders(),  HttpStatus.CREATED);
    }

    @PutMapping("hospitals/{id}")
    public ResponseEntity updateHospital (@Valid @RequestBody Hospital newHospital, @PathVariable Integer id, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap!=null) return errorMap;

        Hospital hospital = hospitalsService.editHospital(newHospital);
        return new ResponseEntity(hospital, HttpStatus.OK);
    }
}
