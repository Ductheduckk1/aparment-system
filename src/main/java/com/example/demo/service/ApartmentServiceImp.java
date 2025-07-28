package com.example.demo.service;

import com.example.demo.model.Apartments;
import com.example.demo.repository.ApartmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartmentServiceImp implements ApartmentService {

    @Autowired
    private ApartmentsRepository apartmentsRepository;

    @Override
    public Apartments createApartment(Apartments apartments) {
        return apartmentsRepository.save(apartments);
    }

    @Override
    public Apartments getApartmentById(int id) {
        return apartmentsRepository.findById(id).orElse(null);
    }

    @Override
    public List<Apartments> getAllAparment() {
        return apartmentsRepository.findAll();
    }

    @Override
    public Apartments update(Apartments apartments, int id) {
        Apartments apartments1 = getApartmentById(id);
        if (apartments1 == null) {
            return null;
        } else {
            apartments1.setArea(apartments.getArea());
            apartments1.setNumRooms(apartments.getNumRooms());
            apartments1.setPhone(apartments.getPhone());
            apartments1.setEmail(apartments.getEmail());
            apartments1.setNote(apartments.getNote());
            return  apartmentsRepository.save(apartments1);
        }
    }

    @Override
    public void deleteApartment(int id) {
        apartmentsRepository.deleteById(id);
    }
}
