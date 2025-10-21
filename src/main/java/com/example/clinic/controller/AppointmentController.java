package com.example.clinic.controller;

import com.example.clinic.dto.ApiResponse;
import com.example.clinic.repo.AppointmentRepository;
import com.example.clinic.repo.PatientRepository;
import com.example.clinic.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentRepository repo;
    private final TokenService tokens;
    private final PatientRepository patients;

    @GetMapping("/mine")
    public ResponseEntity<?> mine(@RequestHeader("Authorization") String auth) {
        String token = auth.replace("Bearer ","");
        if(!tokens.validate(token))
            return ResponseEntity.status(401).body(new ApiResponse<>(false,"Invalid token",null));
        String email = tokens.subject(token);
        Long patientId = patients.findByEmail(email).orElseThrow().getId();
        return ResponseEntity.ok(new ApiResponse<>(true,"OK",repo.findByPatientId(patientId)));
    }
}
