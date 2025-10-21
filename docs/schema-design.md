# Relational Schema (MySQL)

**Doctor**(id PK bigint AI, full_name varchar(120), email varchar(120) UNIQUE,
phone varchar(40), specialty varchar(80), available_date date,
available_times JSON, created_at datetime)

**Patient**(id PK bigint AI, full_name varchar(120), email varchar(120) UNIQUE,
phone varchar(40), birth_date date, created_at datetime)

**Appointment**(id PK bigint AI, doctor_id FK→Doctor(id), patient_id FK→Patient(id),
appointment_time datetime NOT NULL, status enum('BOOKED','CANCELLED','DONE'))

**Prescription**(id PK bigint AI, appointment_id FK→Appointment(id),
doctor_id FK→Doctor(id), patient_id FK→Patient(id),
medication text, dosage varchar(120), notes text, created_at datetime)

**Relationships**
- Doctor(1) — (M) Appointment
- Patient(1) — (M) Appointment
- Appointment(1) — (0..1) Prescription
