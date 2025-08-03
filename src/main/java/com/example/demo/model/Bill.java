package com.example.demo.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "bills")
@Builder
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @Enumerated(EnumType.STRING)
    @Column(name = "bill_type", nullable = false)
    private BillType billType;

    private Float amount;

    private Float usages;

    @Column(name = "due_date")
    private LocalDate dueDate;

    private Boolean paid = false;

    @Column(name = "created_at")
    private LocalDate createdAt = LocalDate.now();

}
enum BillType {
    ELECTRIC,
    WATER
}