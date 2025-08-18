package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Apartment;
import com.example.demo.repository.ApartmentsRepository;
import com.example.demo.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApartmentServiceImp implements ApartmentService {

    @Autowired
    private ApartmentsRepository apartmentsRepository;

    @Override
    public Apartment createApartment(Apartment apartment) {
        return apartmentsRepository.save(apartment);
    }

    @Override
    public Apartment getApartmentById(int id) {
        return apartmentsRepository.findById(id).orElseThrow(() -> new  ResourceNotFoundException("apartment not found with id: " + id));
    }

    @Override
    public List<Apartment> getAllAparment() {
        return apartmentsRepository.findAll();
    }

    @Override
    public Apartment update(Apartment apartment, int id) {
        Apartment apartment1 = getApartmentById(id);
        if (apartment1 == null) {
            return null;
        } else {
            apartment1.setArea(apartment.getArea());
            apartment1.setNumRooms(apartment.getNumRooms());
            apartment1.setPhone(apartment.getPhone());
            apartment1.setEmail(apartment.getEmail());
            apartment1.setNote(apartment.getNote());
            return  apartmentsRepository.save(apartment1);
        }
    }

    @Override
    public void deleteApartment(int id) {
        apartmentsRepository.deleteById(id);
    }

    @Override
    public Optional<Apartment> findById(int id) {
        return apartmentsRepository.findById(id);
    }
}
