package com.aleksic.medapp.controllers;

import com.aleksic.medapp.models.Specialization;
import com.aleksic.medapp.services.SpecializationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class SpecializationController {
    @Autowired
    private SpecializationsService specializationsService;

    @GetMapping("/specializations/all")
    public ResponseEntity<List> getAllSpecializationsWithoutPagination() {
        List<Specialization> specs = specializationsService.getAllSpecializations();
        return new ResponseEntity(specs, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/specializations")
    public ResponseEntity<Map<String, Object>> getAlSpecializations(@RequestParam(defaultValue = "0") Integer pageNo,
                                                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                                                    @RequestParam(defaultValue = "id") String sortBy) {

        Map<String, Object> allSpecializations = specializationsService.getAllSpecializations(pageNo, pageSize, sortBy);
        return new ResponseEntity<Map<String, Object>>(allSpecializations, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/specializations/search")
    public ResponseEntity<Map<String, Object>> getAlSpecializations(@RequestParam String name,
                                                                    @RequestParam(defaultValue = "0") Integer pageNo,
                                                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                                                    @RequestParam(defaultValue = "id") String sortBy) {

        Map<String, Object> allSpecializations = specializationsService.getSpecializationByName(name, pageNo, pageSize, sortBy);
        return new ResponseEntity<Map<String, Object>>(allSpecializations, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/specializations/{id}")
    public Specialization getSpecialization(@PathVariable Integer id) throws ResponseStatusException {
        try {
            return specializationsService.getSpecialization(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Specialization not found");
        }
    }

    @PostMapping(path = "/specializations")
    public Specialization addNewSpecialization(@RequestBody Specialization spec) {
        return specializationsService.addSpecialization(spec);
    }

    @PutMapping("/specializations/{id}")
    public Specialization updateSpecialization(@RequestBody Specialization newSpec, @PathVariable Integer id) {
        return specializationsService.updateSpecialization(newSpec, id);
    }

    @DeleteMapping("/specializations/{id}")
    void deleteSpecialization(@PathVariable Integer id) {
        specializationsService.deleteSpecialization(id);
    }
}
