package com.example.demo.service;

import com.example.demo.model.Bill;

import java.util.List;
import java.util.Optional;

public interface BillService {

    List<Bill> getAllBills();

    Optional<Bill> getBillById(Integer id);

    Bill saveBill(Bill bill);

    Bill updateBill(Integer id, Bill bill);

    void deleteBill(Integer id);
}
