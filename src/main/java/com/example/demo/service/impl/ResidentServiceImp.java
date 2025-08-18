package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Resident;
import com.example.demo.repository.ResidentRepository;
import com.example.demo.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ResidentServiceImp implements ResidentService {
    @Autowired
    private ResidentRepository residentRepository;

    @Override
    public Resident  createResident(Resident resident) {
        return residentRepository.save(resident);
    }

    @Override
    public Resident getResidentById(int id){
        return residentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resident not found with id " + id));
    }

    @Override
    public List<Resident> getAllResident() {
        return List.of();
    }

    @Override
    public Resident update(Resident resident, int id) {
        Resident resident1 = getResidentById(id);
        if(resident1== null) return null;
        else{
            resident1.setName(resident.getName());
            resident1.setEmail(resident.getEmail());
            resident1.setPhone(resident.getPhone());
            resident1.setIdNumber(resident1.getIdNumber());
            resident1.setDob(resident.getDob());
            resident1.setGender(resident.getGender());
            return residentRepository.save(resident1);
        }
    }

    @Override
    public void deleteResident(int id) {
        residentRepository.deleteById(id);
    }
}
