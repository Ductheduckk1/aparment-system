package com.example.demo.controller;


import com.example.demo.model.Resident;
import com.example.demo.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/residents")
public class ResidentController {
    @Autowired
    private ResidentService residentService;

    @PostMapping
    public ResponseEntity<Resident> createResident(@RequestBody Resident resident){
        Resident resident1 = residentService.createResident(resident);
        return ResponseEntity.ok(resident1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resident> updateResident(@RequestBody Resident resident, @PathVariable int id){
        Resident resident1 = residentService.update(resident, id);
        return ResponseEntity.ok(resident1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resident> getResident(@PathVariable int id){
        Resident resident = residentService.getResidentById(id);
        return ResponseEntity.ok(resident);
    }

    @GetMapping
    public ResponseEntity<List<Resident>> getAllResidents(){
        List<Resident> residents = residentService.getAllResident();
        return ResponseEntity.ok(residents);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResident(@PathVariable int id){
        residentService.deleteResident(id);
        return ResponseEntity.noContent().build();
    }

}
