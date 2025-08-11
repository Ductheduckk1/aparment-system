package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
@Entity
@Builder
public class User {
    @Id
    @GeneratedValue
    private int id;
    @Column(name="user_name",nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;

    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;
}
