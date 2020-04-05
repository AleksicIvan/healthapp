package com.aleksic.medapp.controllers;

import com.aleksic.medapp.models.Doctor;
import com.aleksic.medapp.services.DoctorsService;
import com.aleksic.medapp.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class DoctorController {
    @Autowired
    private DoctorsService doctorsService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @GetMapping("/doctors")
    public Iterable<Doctor> getAllDoctors () {
        return doctorsService.getAllDoctors();
    }

    @GetMapping("/doctors/search")
    public List<Doctor> getDoctorsBySearch (@RequestParam(required = false) String q) {
        System.out.println("q: " + q);
        return doctorsService.getDoctorsWithQParam(q);
    }

    @PostMapping(path="/doctors")
    public ResponseEntity<?> addNewDoctor (@Valid @RequestBody Doctor doctor, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);

        if (errorMap != null) return errorMap;

        Doctor newDoctor = doctorsService.addDoctor(doctor);
        return new ResponseEntity<Doctor>(HttpStatus.CREATED);
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
