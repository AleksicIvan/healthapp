package com.aleksic.medapp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "health_checks")
public class HealthCheck {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Nullable
    private String description;

    @OneToOne
    private Hospital hospital;

    @JsonFormat(pattern = "dd-mm-yyyy")
    private Date createdAt;

    @ManyToOne
    @NotNull
    private User user;

    @ManyToOne
    @NotNull
    private Doctor doctor;

    @OneToMany(mappedBy = "healthCheck")
    @JsonManagedReference
    @Nullable
    private List<Report> reports;
}
