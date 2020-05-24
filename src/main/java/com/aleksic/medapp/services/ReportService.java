package com.aleksic.medapp.services;

import com.aleksic.medapp.models.Report;
import com.aleksic.medapp.repositories.ReportRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.SequenceGenerator;

@Data
@Service
@SequenceGenerator(name = "seq", initialValue = 100, allocationSize = 1000000)
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;

    public Report saveReport(Report report) {
        return (Report) reportRepository.save(report);
    }
}
