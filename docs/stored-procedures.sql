-- Q19 (show tables)
SHOW TABLES;

-- Q20 (exactly 5 patients)
SELECT * FROM Patient LIMIT 5;

-- Q21: Daily appointment report by doctor
DELIMITER //
CREATE PROCEDURE GetDailyAppointmentReportByDoctor(IN p_doctor_id BIGINT, IN p_date DATE)
BEGIN
SELECT a.id, a.appointment_time, p.full_name AS patient
FROM Appointment a
         JOIN Patient p ON p.id = a.patient_id
WHERE a.doctor_id = p_doctor_id
  AND DATE(a.appointment_time) = p_date
ORDER BY a.appointment_time;
END //
DELIMITER ;

-- Q22: Doctor with most patients by month
DELIMITER //
CREATE PROCEDURE GetDoctorWithMostPatientsByMonth(IN p_year INT, IN p_month INT)
BEGIN
SELECT d.id, d.full_name, COUNT(DISTINCT a.patient_id) AS patients
FROM Appointment a JOIN Doctor d ON d.id=a.doctor_id
WHERE YEAR(a.appointment_time)=p_year AND MONTH(a.appointment_time)=p_month
GROUP BY d.id, d.full_name ORDER BY patients DESC LIMIT 1;
END //
DELIMITER ;

-- Q23: Doctor with most patients by year
DELIMITER //
CREATE PROCEDURE GetDoctorWithMostPatientsByYear(IN p_year INT)
BEGIN
SELECT d.id, d.full_name, COUNT(DISTINCT a.patient_id) AS patients
FROM Appointment a JOIN Doctor d ON d.id=a.doctor_id
WHERE YEAR(a.appointment_time)=p_year
GROUP BY d.id, d.full_name ORDER BY patients DESC LIMIT 1;
END //
DELIMITER ;
