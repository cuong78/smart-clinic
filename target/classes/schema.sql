-- Smart Clinic Database Schema

-- Drop tables if they exist
DROP TABLE IF EXISTS prescriptions;
DROP TABLE IF EXISTS appointments;
DROP TABLE IF EXISTS patients;
DROP TABLE IF EXISTS doctors;

-- Create doctors table
CREATE TABLE doctors (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    specialization VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    license_number VARCHAR(50),
    bio TEXT,
    available BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create patients table
CREATE TABLE patients (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    date_of_birth DATE,
    gender VARCHAR(10),
    address TEXT,
    blood_group VARCHAR(5),
    medical_history TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create appointments table
CREATE TABLE appointments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    doctor_id BIGINT NOT NULL,
    appointment_date TIMESTAMP NOT NULL,
    status VARCHAR(20) DEFAULT 'PENDING',
    reason TEXT,
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patients(id) ON DELETE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctors(id) ON DELETE CASCADE
);

-- Create prescriptions table
CREATE TABLE prescriptions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    appointment_id BIGINT NOT NULL,
    patient_id BIGINT NOT NULL,
    doctor_id BIGINT NOT NULL,
    medications TEXT NOT NULL,
    diagnosis TEXT,
    instructions TEXT,
    prescription_date TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (appointment_id) REFERENCES appointments(id) ON DELETE CASCADE,
    FOREIGN KEY (patient_id) REFERENCES patients(id) ON DELETE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctors(id) ON DELETE CASCADE
);

-- Create indexes for better performance
CREATE INDEX idx_appointments_patient ON appointments(patient_id);
CREATE INDEX idx_appointments_doctor ON appointments(doctor_id);
CREATE INDEX idx_appointments_status ON appointments(status);
CREATE INDEX idx_appointments_date ON appointments(appointment_date);
CREATE INDEX idx_prescriptions_patient ON prescriptions(patient_id);
CREATE INDEX idx_prescriptions_doctor ON prescriptions(doctor_id);
CREATE INDEX idx_prescriptions_appointment ON prescriptions(appointment_id);

-- Insert sample data
INSERT INTO doctors (name, email, password, specialization, phone, license_number, bio, available) VALUES
('Dr. John Smith', 'john.smith@clinic.com', '$2a$10$xZJxgHN5HQH0qQZqZqZqZe5ZqZqZqZqZqZqZqZqZqZqZqZqZqZqZq', 'Cardiology', '123-456-7890', 'MD-12345', 'Experienced cardiologist with 15 years of practice', TRUE),
('Dr. Sarah Johnson', 'sarah.johnson@clinic.com', '$2a$10$xZJxgHN5HQH0qQZqZqZqZe5ZqZqZqZqZqZqZqZqZqZqZqZqZqZqZq', 'Pediatrics', '123-456-7891', 'MD-12346', 'Specializing in child healthcare', TRUE),
('Dr. Michael Chen', 'michael.chen@clinic.com', '$2a$10$xZJxgHN5HQH0qQZqZqZqZe5ZqZqZqZqZqZqZqZqZqZqZqZqZqZqZq', 'Orthopedics', '123-456-7892', 'MD-12347', 'Expert in bone and joint disorders', TRUE);

INSERT INTO patients (name, email, password, phone, date_of_birth, gender, address, blood_group) VALUES
('Jane Doe', 'jane.doe@email.com', '$2a$10$xZJxgHN5HQH0qQZqZqZqZe5ZqZqZqZqZqZqZqZqZqZqZqZqZqZqZq', '555-123-4567', '1990-05-15', 'Female', '123 Main St, City, State', 'A+'),
('Robert Brown', 'robert.brown@email.com', '$2a$10$xZJxgHN5HQH0qQZqZqZqZe5ZqZqZqZqZqZqZqZqZqZqZqZqZqZqZq', '555-123-4568', '1985-08-20', 'Male', '456 Oak Ave, City, State', 'O+');
