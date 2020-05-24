package com.aleksic.medapp.controllers;

import com.aleksic.medapp.models.Doctor;
import com.aleksic.medapp.models.Report;
import com.aleksic.medapp.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ReportController {
    @Autowired
    private ReportService reportService;

    @PostMapping(path="/reports")
    public ResponseEntity<?> addNewReport (@Valid @RequestBody Report report, BindingResult result) {
        Report newReport = reportService.saveReport(report);
        return new ResponseEntity<Report>(newReport, new HttpHeaders(), HttpStatus.CREATED);
    }
}
