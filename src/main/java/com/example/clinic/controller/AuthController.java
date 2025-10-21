package com.example.clinic.controller;

import com.example.clinic.dto.ApiResponse;
import com.example.clinic.dto.LoginRequest;
import com.example.clinic.repo.PatientRepository;
import com.example.clinic.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final TokenService tokens;
    private final PatientRepository patients;

    @PostMapping("/token")
    public ApiResponse<String> token(@RequestBody LoginRequest login) {
        boolean ok = patients.findByEmail(login.email()).isPresent()
                && "password".equals(login.password());
        return ok ? new ApiResponse<>(true,"OK",tokens.generateToken(login.email()))
                : new ApiResponse<>(false,"Invalid credentials",null);
    }
}

