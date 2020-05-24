package com.aleksic.medapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Integer id;
    private String s3FileUrl;

    @ManyToOne
    @JsonBackReference
    private HealthCheck healthCheck;
}
