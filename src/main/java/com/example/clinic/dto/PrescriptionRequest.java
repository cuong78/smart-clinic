package com.example.clinic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public record PrescriptionRequest(Long appointmentId, String medication, String dosage, String notes) {}

