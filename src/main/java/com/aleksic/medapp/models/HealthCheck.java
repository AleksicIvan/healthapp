package com.aleksic.medapp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.print.Doc;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "health_check")
public class HealthCheck {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
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
}
