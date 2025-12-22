package awithd.finalproject.service.impl;

import awithd.finalproject.dto.AppointmentDto;
import awithd.finalproject.entity.Appointment;
import awithd.finalproject.mapper.AppointmentMapper;
import awithd.finalproject.repository.AppointmentRepository;
import awithd.finalproject.repository.DoctorRepository;
import awithd.finalproject.repository.PatientRepository;
import awithd.finalproject.service.AppointmentService;
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
    public AppointmentDto create(AppointmentDto appointmentDto) {
        return appointmentMapper.toDto(appointmentRepository.save(appointmentMapper.toEntity(appointmentDto)));
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
    public AppointmentDto update(Long id, AppointmentDto appointmentDto) {
        Appointment oldEntity = appointmentRepository.findById(id).orElseThrow();

        oldEntity.setAppointmentTime(appointmentDto.getAppointmentTimeDto());
        oldEntity.setStatus(appointmentDto.getStatusDto());
        oldEntity.setReason(appointmentDto.getReasonDto());
        oldEntity.setDoctor(doctorRepository.findById(appointmentDto.getDoctorId()).orElseThrow());
        oldEntity.setPatient(patientRepository.findById(appointmentDto.getPatientId()).orElseThrow());

        return appointmentMapper.toDto(appointmentRepository.save(oldEntity));
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
