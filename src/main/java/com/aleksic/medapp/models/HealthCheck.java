package com.aleksic.medapp.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "health_checks")
@SequenceGenerator(name = "seq", initialValue = 100, allocationSize = 1000000)
public class HealthCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Integer id;
    @Nullable
    private String description;

    @Nullable
    private Integer doctorRating;

    @OneToOne
    @Valid
    private Hospital hospital;

    @NotNull
    private Date createdAt;

    @ManyToOne
    @NotNull
    private User user;

    @ManyToOne
    @NotNull
    @Valid
    private Doctor doctor;

    @OneToMany(mappedBy = "healthCheck", orphanRemoval = true)
    @JsonManagedReference
    @Nullable
    private List<Report> reports;
}
