package com.example.clinic.repo;

import com.example.clinic.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorIdAndAppointmentTimeBetween(Long docId,
                                                              LocalDateTime start, LocalDateTime end);
    List<Appointment> findByPatientId(Long patientId);
}
