package com.example.demo.service;

import com.example.demo.model.Resident;

import java.util.List;

public interface ResidentService {
    Resident createResident(Resident resident);
    Resident getResidentById(int id);
    List<Resident> getAllResident();
    Resident update(Resident resident, int id);
    void deleteResident(int id);
}