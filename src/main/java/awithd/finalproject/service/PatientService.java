package awithd.finalproject.service;

import awithd.finalproject.dto.PatientDto;

import java.util.List;

public interface PatientService {
    PatientDto create(PatientDto patientDto);
    PatientDto getById(Long id);
    List<PatientDto> getAll();
    PatientDto update(Long id, PatientDto patientDto);
    boolean delete(Long id);
}
