package com.example.clinic.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
public class Doctor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=120) private String fullName;
    @Column(nullable=false, unique=true, length=120) private String email;
    @Column(length=40) private String phone;
    @Column(length=80) private String specialty;

    /** Availability per date; times are stored separately. */
    private LocalDate availableDate;

    // Correct type & annotation for simple times collection
    @ElementCollection
    @CollectionTable(name="doctor_available_times", joinColumns=@JoinColumn(name="doctor_id"))
    @Column(name="time_slot", nullable=false)
    private List<LocalTime> availableTimes;

    // getters/setters…
}
