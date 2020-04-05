package com.aleksic.medapp.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int Id;

    @Getter
    @Setter
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @Getter
    @Setter
    private Address address;
}

