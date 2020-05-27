package com.aleksic.medapp.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "specializations")
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
}