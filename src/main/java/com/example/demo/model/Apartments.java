package com.example.demo.model;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "apartments")
@Builder
public class Apartments {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private float area;

    @Column(name="num_rooms")

    private int numRooms;

    private String phone;

    private String email;

    private String note;
}
