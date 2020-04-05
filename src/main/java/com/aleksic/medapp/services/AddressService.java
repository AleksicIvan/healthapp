package com.aleksic.medapp.services;

import com.aleksic.medapp.models.Address;
import com.aleksic.medapp.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAddresses () {
        return addressRepository.findAll();
    }
}
