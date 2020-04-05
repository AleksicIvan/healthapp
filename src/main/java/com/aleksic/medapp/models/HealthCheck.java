package com.aleksic.medapp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.print.Doc;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Table(name = "health_check")
public class HealthCheck {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String description;

    @OneToOne
    @Getter
    @Setter
    private Hospital hospital;

    @Getter
    @Setter
    @JsonFormat(pattern = "dd-mm-yyyy")
    private Date createdAt;

    @ManyToOne
    @Getter
    @Setter
    @NotNull
    private User user;

    @ManyToOne
    @Getter
    @Setter
    @NotNull
    private Doctor doctor;
}
