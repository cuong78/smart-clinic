package com.example.clinic.controller;

import com.example.clinic.dto.ApiResponse;
import com.example.clinic.dto.PrescriptionRequest;
import com.example.clinic.entity.Appointment;
import com.example.clinic.entity.Doctor;
import com.example.clinic.entity.Patient;
import com.example.clinic.entity.Prescription;
import com.example.clinic.repo.AppointmentRepository;
import com.example.clinic.repo.DoctorRepository;
import com.example.clinic.repo.PatientRepository;
import com.example.clinic.repo.PrescriptionRepository;
import com.example.clinic.service.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
// controller/PrescriptionController.java  (Q7)
@RestController @RequestMapping("/api/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {
    private final PrescriptionRepository repo;
    private final AppointmentRepository apptRepo;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody PrescriptionRequest req,
                                  @RequestHeader("Authorization") String auth) { // token + body validation (3+3 pts)
        String token = auth.replace("Bearer ", "");
        if(!tokenService.validate(token))
            return ResponseEntity.status(401)
                    .body(new ApiResponse<>(false,"Invalid token",null));

        var appt = apptRepo.findById(req.appointmentId()).orElseThrow();
        var p = new Prescription();
        p.setAppointment(appt);
        p.setDoctor(appt.getDoctor());
        p.setPatient(appt.getPatient());
        p.setMedication(req.medication());
        p.setDosage(req.dosage());
        p.setNotes(req.notes());
        return ResponseEntity.ok(new ApiResponse<>(true,"Saved",repo.save(p)));
    }
}
