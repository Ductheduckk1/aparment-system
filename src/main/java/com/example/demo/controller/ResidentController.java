package com.example.demo.controller;


import com.example.demo.model.Resident;
import com.example.demo.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/residents")
public class ResidentController {
    private final ResidentService residentService;
    @Autowired
    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Resident> createResident(@RequestBody Resident resident){
        Resident resident1 = residentService.createResident(resident);
        return ResponseEntity.ok(resident1);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Resident> updateResident(@RequestBody Resident resident, @PathVariable int id){
        Resident resident1 = residentService.update(resident, id);
        return ResponseEntity.ok(resident1);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Resident> getResidentById(@PathVariable int id){
        Resident resident = residentService.getResidentById(id);
        return ResponseEntity.ok(resident);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<Resident>> getAllResidents(){
        List<Resident> residents = residentService.getAllResident();
        return ResponseEntity.ok(residents);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteResident(@PathVariable int id){
        residentService.deleteResident(id);
        return ResponseEntity.noContent().build();
    }

}
