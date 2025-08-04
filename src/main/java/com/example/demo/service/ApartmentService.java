package com.example.demo.service;


import com.example.demo.model.Apartment;

import java.util.List;
import java.util.Optional;

public interface ApartmentService {
    Apartment createApartment(Apartment apartment);
    Apartment getApartmentById(int id);
    List<Apartment> getAllAparment();
    Apartment update(Apartment apartment, int id);
    void deleteApartment(int id);
    Optional<Apartment> findById(int id);
}
