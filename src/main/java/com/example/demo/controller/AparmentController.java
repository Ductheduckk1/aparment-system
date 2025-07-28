package com.example.demo.controller;


import com.example.demo.model.Apartments;
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
    public ResponseEntity<Apartments> createApartment(@RequestBody Apartments apartments){
        Apartments apartments1 = apartmentService.createApartment(apartments);
        return ResponseEntity.ok(apartments1);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Apartments> updateApartment(@RequestBody Apartments apartments, @PathVariable int id){
        Apartments apartments1 = apartmentService.update(apartments, id);
        return ResponseEntity.ok(apartments1);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Apartments> getApartments(@PathVariable int id){
        Apartments apartments = apartmentService.getApartmentById(id);
        return ResponseEntity.ok(apartments);
    }
    @GetMapping
    public ResponseEntity<List<Apartments>> getAllApartments(){
        List<Apartments> apartments = apartmentService.getAllAparment();
        return ResponseEntity.ok(apartments);
    }
    @PostMapping("/{id}")
    public ResponseEntity<Apartments> deleteApartment(@PathVariable int id){
        apartmentService.deleteApartment(id);
        return ResponseEntity.noContent().build();
    }
}
