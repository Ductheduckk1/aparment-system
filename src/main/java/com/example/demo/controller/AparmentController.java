package com.example.demo.controller;


import com.example.demo.model.Apartment;
import com.example.demo.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apartments")
public class AparmentController {
    @Autowired
    private ApartmentService apartmentService;

    @PostMapping
    public ResponseEntity<Apartment> createApartment(@RequestBody Apartment apartment){
        Apartment apartment1 = apartmentService.createApartment(apartment);
        return ResponseEntity.ok(apartment1);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Apartment> updateApartment(@RequestBody Apartment apartment, @PathVariable int id){
        Apartment apartment1 = apartmentService.update(apartment, id);
        return ResponseEntity.ok(apartment1);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Apartment> getApartments(@PathVariable int id){
        Apartment apartment = apartmentService.getApartmentById(id);
        return ResponseEntity.ok(apartment);
    }
    @GetMapping
    public ResponseEntity<List<Apartment>> getAllApartments(){
        List<Apartment> apartments = apartmentService.getAllAparment();
        return ResponseEntity.ok(apartments);
    }
    @PostMapping("/{id}")
    public ResponseEntity<Apartment> deleteApartment(@PathVariable int id){
        apartmentService.deleteApartment(id);
        return ResponseEntity.noContent().build();
    }
}
