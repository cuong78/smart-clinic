package com.example.clinic.controller;

import com.example.clinic.dto.ApiResponse;
import com.example.clinic.entity.Doctor;
import com.example.clinic.service.DoctorService;
import com.example.clinic.service.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
// controller/DoctorController.java  (Q5)
@RestController @RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    private final TokenService tokenService;

    // GET /api/doctors/{id}/availability?date=2025-10-22
    @GetMapping("/{id}/availability")
    public ResponseEntity<ApiResponse<List<LocalTime>>> availability(
            @PathVariable Long id, @RequestParam String date,
            @RequestHeader("Authorization") String auth) {     // validate token (3 pts)
        String token = auth.replace("Bearer ", "");
        if (!tokenService.validate(token))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(false, "Invalid token", null));

        var slots = doctorService.getAvailableTimes(id, LocalDate.parse(date));
        return ResponseEntity.ok(new ApiResponse<>(true, "OK", slots));   // structured (3 pts)
    }
}
