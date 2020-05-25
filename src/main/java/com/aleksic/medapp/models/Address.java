package com.aleksic.medapp.models;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String city;
    private String street;
}
