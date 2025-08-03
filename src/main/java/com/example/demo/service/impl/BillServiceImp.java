package com.example.demo.service.impl;

import com.example.demo.model.Bill;
import com.example.demo.repository.BillRepository;
import com.example.demo.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImp implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Override
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    @Override
    public Optional<Bill> getBillById(Integer id) {
        return billRepository.findById(id);
    }

    @Override
    public Bill saveBill(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public Bill updateBill(Integer id, Bill bill) {
        Optional<Bill> existing = billRepository.findById(id);
        if (existing.isPresent()) {
            Bill updated = existing.get();
            updated.setApartment(bill.getApartment());
            updated.setBillType(bill.getBillType());
            updated.setAmount(bill.getAmount());
            updated.setUsages(bill.getUsages());
            updated.setDueDate(bill.getDueDate());
            updated.setPaid(bill.getPaid());
            return billRepository.save(updated);
        } else {
            throw new RuntimeException("Bill not found with id " + id);
        }
    }

    @Override
    public void deleteBill(Integer id) {
        billRepository.deleteById(id);
    }
}
