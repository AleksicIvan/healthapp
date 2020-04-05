package com.aleksic.medapp.repositories;

import com.aleksic.medapp.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
