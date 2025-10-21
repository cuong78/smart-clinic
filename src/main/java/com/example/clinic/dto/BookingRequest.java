package com.example.clinic.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

public record BookingRequest(Long doctorId, Long patientId, String dateTimeIso) {}

