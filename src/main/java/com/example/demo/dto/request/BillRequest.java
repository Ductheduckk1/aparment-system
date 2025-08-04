package com.example.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillRequest {
    private Integer apartmentId;
    private int electricUsages;
    private int waterUsages;
    private LocalDate dueDate; // ISO format (yyyy-MM-dd)
    private Boolean paid;   // optional, default false
}
