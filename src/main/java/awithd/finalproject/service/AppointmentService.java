package awithd.finalproject.service;

import awithd.finalproject.dto.AppointmentDto;
import awithd.finalproject.dto.DoctorDto;

import java.util.List;

public interface AppointmentService {
    AppointmentDto create(AppointmentDto appointmentDto);
    AppointmentDto getById(Long id);
    List<AppointmentDto> getAll();
    AppointmentDto update(Long id, AppointmentDto appointmentDto);
    boolean delete(Long id);
}
