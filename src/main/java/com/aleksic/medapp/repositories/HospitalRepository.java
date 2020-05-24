package com.aleksic.medapp.repositories;

import com.aleksic.medapp.models.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    Page<Hospital> findAll(Pageable pageable);
    Page<Hospital> findHospitalByAddressCityContainingIgnoreCase (String city, Pageable pageable);
    Page<Hospital> findHospitalByNameContainingIgnoreCase (String name, Pageable pageable);
    List<Hospital> findHospitalByAddressStreetContainingIgnoreCase (String street);
}
