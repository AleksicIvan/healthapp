package com.aleksic.medapp.services;

import com.aleksic.medapp.models.Report;
import com.aleksic.medapp.repositories.ReportRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;

    public Report saveReport(Report report) {
        return (Report) reportRepository.save(report);
    }
}
