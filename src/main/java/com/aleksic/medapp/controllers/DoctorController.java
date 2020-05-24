package com.aleksic.medapp.controllers;

import com.aleksic.medapp.models.Doctor;
import com.aleksic.medapp.services.DoctorsService;
import com.aleksic.medapp.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class DoctorController {
    @Autowired
    private DoctorsService doctorsService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @GetMapping(value = "/doctors/search", params = "firstName")
    public List<Doctor> getDoctorsByFirstName (@RequestParam(required = false) String firstName) {
        return doctorsService.getDoctorsByFirstName(firstName);
    }

    @GetMapping(value = "/doctors/search", params = "lastName")
    public List<Doctor> getDoctorsByLastName (@RequestParam(required = false) String lastName) {
        return doctorsService.getDoctorsByLastName(lastName);
    }

    @GetMapping(value = "/doctors/search", params = "fullName")
    public List<Doctor> getDoctorsByFullName (@RequestParam(required = false) String fullName) {
        return doctorsService.getDoctorsByFullName(fullName);
    }

    @GetMapping(value = "/doctors/search", params = "specialization")
    public List<Doctor> getDoctorsBySpecialization (@RequestParam(required = false) String specialization) {
        return doctorsService.getDoctorsBySpecialization(specialization);
    }

    @GetMapping("/doctors")
    public ResponseEntity<Map<String, Object>> getAllDoctors (@RequestParam(defaultValue = "0") Integer pageNo,
                                                              @RequestParam(defaultValue = "10") Integer pageSize,
                                                              @RequestParam(defaultValue = "rating") String sortBy) {

        Map<String, Object> allDocs = doctorsService.getAllDoctors(pageNo, pageSize, sortBy);
        return new ResponseEntity<Map<String, Object>>(allDocs, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(path="/doctors")
    public ResponseEntity<?> addNewDoctor (@Valid @RequestBody Doctor doctor, BindingResult result) {
        Doctor newDoctor = doctorsService.addDoctor(doctor);
        return new ResponseEntity(newDoctor, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping("/doctors/{id}")
    public Doctor updateDoctor (@Valid @RequestBody Doctor newDoctor, @PathVariable Integer id) {
        return doctorsService.updateDoctor(newDoctor, id);
    }

    @DeleteMapping("/doctors/{id}")
    void deleteDoctor(@PathVariable Integer id) {
        doctorsService.deleteDoctor(id);
    }
}
