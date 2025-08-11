package com.example.demo.controller;

import com.example.demo.dto.request.BillRequest;
import com.example.demo.dto.response.BillResponse;
import com.example.demo.model.Apartment;
import com.example.demo.model.Bill;
import com.example.demo.service.ApartmentService;
import com.example.demo.service.BillService;
import com.example.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bill")
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private EmailService emailService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<BillResponse> getAllBills() {
        return billService.getAllBills()
                .stream()
                .map(BillResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<BillResponse> getBillById(@PathVariable Integer id) {
        return ResponseEntity.of(
                billService.getBillById(id)
                        .map(BillResponse::fromEntity)
        );
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BillResponse> createBill(@RequestBody BillRequest request) {
        Apartment apartment = apartmentService.findById(request.getApartmentId())
                .orElseThrow(() -> new RuntimeException("Apartment not found with id: " + request.getApartmentId()));

        Bill bill = Bill.builder()
                .apartment(apartment)
                .electricUsages(request.getElectricUsages())
                .waterUsages(request.getWaterUsages())
                .dueDate(request.getDueDate())
                .paid(request.getPaid() != null ? request.getPaid() : false)
                .createdAt(LocalDate.now())
                .build();
        Bill saved = billService.saveBill(bill);
        String recipientEmail = apartment.getEmail(); // giả sử bạn có thông tin email trong Apartment
        String subject = "Your New Bill";
        String content =
                 "Your bill is ready:\n"
                + "Electric: " + bill.getElectricUsages() + " x 3000 = " + (bill.getElectricUsages() * 3000) + "\n"
                + "Water: " + bill.getWaterUsages() + " x 4000 = " + (bill.getWaterUsages() * 4000) + "\n"
                + "Total: " + (bill.getElectricUsages() * 3000L + bill.getWaterUsages() * 4000L) + "\n"
                + "Due date: " + bill.getDueDate() + "\n\n"
                + "Thanks.";

        emailService.sendBillEmail(recipientEmail, subject, content);

        return ResponseEntity.ok(BillResponse.fromEntity(saved));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BillResponse> updateBill(@PathVariable Integer id, @RequestBody BillRequest request) {
        Apartment apartment = apartmentService.findById(request.getApartmentId())
                .orElseThrow(() -> new RuntimeException("Apartment not found with id: " + request.getApartmentId()));

        Bill bill = Bill.builder()
                .apartment(apartment)
                .electricUsages(request.getElectricUsages())
                .waterUsages(request.getWaterUsages())
                .dueDate(request.getDueDate())
                .paid(request.getPaid() != null ? request.getPaid() : false)
                .createdAt(LocalDate.now())
                .build();

        Bill updated = billService.updateBill(id, bill);
        return ResponseEntity.ok(BillResponse.fromEntity(updated));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBill(@PathVariable Integer id) {
        billService.deleteBill(id);
        return ResponseEntity.noContent().build();
    }
}
