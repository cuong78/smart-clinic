package com.example.clinic.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data

public class Prescription {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @OneToOne @JoinColumn(name="appointment_id") private Appointment appointment;
    @ManyToOne @JoinColumn(name="doctor_id") private Doctor doctor;
    @ManyToOne @JoinColumn(name="patient_id") private Patient patient;

    @Column(columnDefinition="TEXT") private String medication;
    private String dosage;
    @Column(columnDefinition="TEXT") private String notes;
    private LocalDateTime createdAt = LocalDateTime.now();
    // getters/setters…
}
