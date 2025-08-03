package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "residents")
@Builder
public class Resident {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String email;

    private String phone;

    @Column(name="identity_number",unique = true)
    private String idNumber;

    private String dob;

    private String gender;

}
