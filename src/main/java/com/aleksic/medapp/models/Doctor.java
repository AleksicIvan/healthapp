package com.aleksic.medapp.models;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String fullName;

    private Integer rating;
    private Integer allRatings;
    private Double noOfRatings;

    @ManyToOne
    private Specialization specialization;
}