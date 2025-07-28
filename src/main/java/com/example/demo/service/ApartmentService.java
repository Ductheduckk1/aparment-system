package com.example.demo.service;


import com.example.demo.model.Apartments;

import java.util.List;

public interface ApartmentService {
    Apartments createApartment(Apartments apartments);
    Apartments getApartmentById(int id);
    List<Apartments> getAllAparment();
    Apartments update(Apartments apartments, int id);
    void deleteApartment(int id);
}
