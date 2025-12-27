package awithd.finalproject.service.impl;

import awithd.finalproject.dto.AppointmentDto;
import awithd.finalproject.entity.Appointment;
import awithd.finalproject.entity.AppointmentStatus;
import awithd.finalproject.entity.Doctor;
import awithd.finalproject.entity.Patient;
import awithd.finalproject.mapper.AppointmentMapper;
import awithd.finalproject.repository.AppointmentRepository;
import awithd.finalproject.repository.DoctorRepository;
import awithd.finalproject.repository.PatientRepository;
import awithd.finalproject.service.AppointmentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Override
    @Transactional
    public AppointmentDto create(AppointmentDto dto) {

        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        if (dto.getAppointmentTimeDto() != null) {
            appointment.setAppointmentTime(dto.getAppointmentTimeDto());
        }        appointment.setStatus(dto.getStatusDto());
        appointment.setReason(dto.getReasonDto());
        appointment.setStatus(dto.getStatusDto() != null ? dto.getStatusDto() : AppointmentStatus.CREATED);
        return appointmentMapper.toDto(appointmentRepository.save(appointment));
    }


    @Override
    public AppointmentDto getById(Long id) {
        return appointmentMapper.toDto(appointmentRepository.findById(id).orElseThrow());
    }

    @Override
    public List<AppointmentDto> getAll() {
        return appointmentMapper.toDtoList(appointmentRepository.findAll());
    }

    @Override
    @Transactional
    public AppointmentDto update(Long id, AppointmentDto dto) {

        Appointment old = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        if (dto.getAppointmentTimeDto() != null) {
            old.setAppointmentTime(dto.getAppointmentTimeDto());
        }

        if (dto.getStatusDto() != null) {
            old.setStatus(dto.getStatusDto());
        }

        if (dto.getReasonDto() != null) {
            old.setReason(dto.getReasonDto());
        }

        if (dto.getDoctorId() != null) {
            old.setDoctor(
                    doctorRepository.findById(dto.getDoctorId())
                            .orElseThrow(() -> new RuntimeException("Doctor not found"))
            );
        }

        if (dto.getPatientId() != null) {
            old.setPatient(
                    patientRepository.findById(dto.getPatientId())
                            .orElseThrow(() -> new RuntimeException("Patient not found"))
            );
        }
        return appointmentMapper.toDto(appointmentRepository.save(old));
    }


    @Override
    public boolean delete(Long id) {
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
