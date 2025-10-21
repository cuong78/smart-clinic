package com.example.clinic.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data

public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable=false,length=120) private String fullName;
    @Column(nullable=false,unique=true,length=120) private String email;
    @Column(length=40) private String phone;
    private LocalDate birthDate;
    // getters/setters…
}
