package awithd.finalproject.service;

import awithd.finalproject.dto.DoctorDto;

import java.util.List;

public interface DoctorService {
    DoctorDto create(DoctorDto doctorDto);
    DoctorDto getById(Long id);
    List<DoctorDto> getAll();
    DoctorDto update(DoctorDto doctorDto);
    boolean delete(Long id);
}
