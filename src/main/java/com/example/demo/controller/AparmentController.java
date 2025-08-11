package com.example.demo.controller;


import com.example.demo.model.Apartment;
import com.example.demo.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apartments")
public class AparmentController {
    @Autowired
    private ApartmentService apartmentService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Apartment> createApartment(@RequestBody Apartment apartment){
        Apartment apartment1 = apartmentService.createApartment(apartment);
        return ResponseEntity.ok(apartment1);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Apartment> updateApartment(@RequestBody Apartment apartment, @PathVariable int id){
        Apartment apartment1 = apartmentService.update(apartment, id);
        return ResponseEntity.ok(apartment1);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Apartment> getApartments(@PathVariable int id){
        Apartment apartment = apartmentService.getApartmentById(id);
        return ResponseEntity.ok(apartment);
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<Apartment>> getAllApartments(){
        List<Apartment> apartments = apartmentService.getAllAparment();
        return ResponseEntity.ok(apartments);
    }
    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Apartment> deleteApartment(@PathVariable int id){
        apartmentService.deleteApartment(id);
        return ResponseEntity.noContent().build();
    }
}
