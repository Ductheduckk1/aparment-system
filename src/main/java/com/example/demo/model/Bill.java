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

    private int electricUsages;

    private int waterUsages;

    @Column(name = "due_date")
    private LocalDate dueDate;

    private Boolean paid = false;

    @Column(name = "created_at")
    private LocalDate createdAt = LocalDate.now();
}
