package com.example.clinic.repo;

import com.example.clinic.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findBySpecialtyContainingIgnoreCase(String specialty);
    Optional<Doctor> findByEmail(String email);
}
