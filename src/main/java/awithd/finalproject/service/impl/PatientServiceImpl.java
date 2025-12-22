package awithd.finalproject.service.impl;

import awithd.finalproject.dto.PatientDto;
import awithd.finalproject.entity.Patient;
import awithd.finalproject.mapper.PatientMapper;
import awithd.finalproject.repository.PatientRepository;
import awithd.finalproject.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public PatientDto create(PatientDto patientDto) {
        return patientMapper.toDto(patientRepository.save(patientMapper.toEntity(patientDto)));
    }

    @Override
    public PatientDto getById(Long id) {
        return patientMapper.toDto(patientRepository.findById(id).orElse(null));
    }

    @Override
    public List<PatientDto> getAll() {
        return patientMapper.toDtoList(patientRepository.findAll());
    }

    @Override
    public PatientDto update(Long id, PatientDto patientDto) {
        Patient newEntity = patientMapper.toEntity(patientDto);
        Patient oldEntity = patientRepository.findById(id).orElseThrow();

        oldEntity.setEmail(newEntity.getEmail());
        oldEntity.setFirstName(newEntity.getFirstName());
        oldEntity.setLastName(newEntity.getLastName());
        oldEntity.setMedicalCardNumber(newEntity.getMedicalCardNumber());

        return patientMapper.toDto(patientRepository.save(oldEntity));
    }

    @Override
    public boolean delete(Long id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
