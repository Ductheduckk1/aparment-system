package com.example.demo.dto.response;


import com.example.demo.model.Bill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BillResponse {
    private Integer id;
    private String apartmentNumber;
    private int electricUsages;
    private int waterUsages;
    private String dueDate;
    private Boolean paid;
    private String createdAt;
    private long total;

    public static BillResponse fromEntity(Bill bill){
        BillResponse response = new BillResponse();
        response.setId(bill.getId());
        response.setApartmentNumber(String.valueOf(bill.getApartment().getNumRooms()));
        response.setElectricUsages(bill.getElectricUsages());
        response.setWaterUsages(bill.getWaterUsages());
        response.setTotal(3000L* bill.getElectricUsages() + 4000L* bill.getWaterUsages());
        response.setDueDate(String.valueOf(bill.getDueDate()));
        response.setPaid(bill.getPaid());
        response.setCreatedAt(bill.getCreatedAt().toString());
        return response;
    }
}
