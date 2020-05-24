package com.aleksic.medapp.repositories;

import com.aleksic.medapp.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Integer> {
}
