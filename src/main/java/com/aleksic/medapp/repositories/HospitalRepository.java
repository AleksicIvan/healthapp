package com.aleksic.medapp.repositories;

import com.aleksic.medapp.models.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    List<Hospital> findHospitalByAddressCityContainingIgnoreCase (String city);
    List<Hospital> findHospitalByNameContainingIgnoreCase (String name);
    List<Hospital> findHospitalByAddressStreetContainingIgnoreCase (String street);
}
