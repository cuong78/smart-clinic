package com.example.clinic.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data

public class Appointment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @ManyToOne(optional=false) @JoinColumn(name="doctor_id")
    private Doctor doctor;

    @ManyToOne(optional=false) @JoinColumn(name="patient_id")
    private Patient patient;

    @NotNull @FutureOrPresent
    private LocalDateTime appointmentTime;

    @Enumerated(EnumType.STRING)
    private Status status = Status.BOOKED;
    public enum Status { BOOKED, CANCELLED, DONE }
    // getters/setters…
}
