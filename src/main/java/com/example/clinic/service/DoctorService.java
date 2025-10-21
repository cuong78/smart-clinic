package com.example.clinic.service;

import com.example.clinic.dto.ApiResponse;
import com.example.clinic.entity.Doctor;
import com.example.clinic.repo.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

// service/DoctorService.java  (Q10)
@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository repo;

    // Return available time slots for doctor on date (3 pts)
    public List<LocalTime> getAvailableTimes(Long doctorId, LocalDate date) {
        return repo.findById(doctorId)
                .filter(d -> date.equals(d.getAvailableDate()))
                .map(Doctor::getAvailableTimes)
                .orElse(List.of());
    }

    // Validate login (email+password) — demo only (2 pts)
    public ApiResponse<String> validateLogin(String email, String password) {
        boolean ok = repo.findByEmail(email).isPresent() && "password".equals(password);
        return new ApiResponse<>(ok, ok ? "Login OK" : "Invalid credentials", null);
    }
}


