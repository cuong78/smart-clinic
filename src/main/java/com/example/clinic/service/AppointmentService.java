package com.example.clinic.service;

import com.example.clinic.dto.BookingRequest;
import com.example.clinic.entity.Appointment;

import com.example.clinic.repo.AppointmentRepository;
import com.example.clinic.repo.DoctorRepository;
import com.example.clinic.repo.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository repo;
    private final DoctorRepository doctorRepo;
    private final PatientRepository patientRepo;

    // Save an appointment (3 pts)
    public Appointment book(BookingRequest req) {
        var doctor = doctorRepo.findById(req.doctorId()).orElseThrow();
        var patient = patientRepo.findById(req.patientId()).orElseThrow();
        var appt = new Appointment();
        appt.setDoctor(doctor);
        appt.setPatient(patient);
        appt.setAppointmentTime(LocalDateTime.parse(req.dateTimeIso()));
        return repo.save(appt);
    }

    // Retrieve appointments for a doctor on a specific date (3 pts)
    public List<Appointment> forDoctorOn(LocalDate date, Long doctorId) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end   = start.plusDays(1);
        return repo.findByDoctorIdAndAppointmentTimeBetween(doctorId, start, end);
    }
}
