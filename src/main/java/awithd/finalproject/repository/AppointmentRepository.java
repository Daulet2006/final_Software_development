package awithd.finalproject.repository;

import awithd.finalproject.entity.Appointment;
import awithd.finalproject.entity.AppointmentStatus;
import awithd.finalproject.entity.Doctor;
import awithd.finalproject.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByDoctor(Doctor doctor);

    List<Appointment> findByPatient(Patient patient);

    List<Appointment> findByDoctorAndStatus(Doctor doctor, AppointmentStatus status);

    List<Appointment> findByPatientAndAppointmentTimeBetween(Patient patient, LocalDateTime start, LocalDateTime end);
}
