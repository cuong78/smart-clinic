package com.example.clinic.repo;

import com.example.clinic.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByEmail(String email);                         // (Q8 2pts)
    Optional<Patient> findByEmailOrPhone(String email, String phone);    // (Q8 2pts)
}
