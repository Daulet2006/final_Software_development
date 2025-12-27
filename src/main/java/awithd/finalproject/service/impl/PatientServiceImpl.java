package awithd.finalproject.service.impl;

import awithd.finalproject.dto.PatientDto;
import awithd.finalproject.entity.Patient;
import awithd.finalproject.entity.Permission;
import awithd.finalproject.entity.User;
import awithd.finalproject.mapper.PatientMapper;
import awithd.finalproject.repository.PatientRepository;
import awithd.finalproject.repository.PermissionRepository;
import awithd.finalproject.repository.UserRepository;
import awithd.finalproject.service.PatientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;

    @Override
    @Transactional
    public PatientDto create(PatientDto dto) {

        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (patientRepository.existsById(user.getId())) {
            throw new RuntimeException("Patient already exists");
        }

        Permission patientRole = permissionRepository
                .findByName("ROLE_PATIENT")
                .orElseThrow(() -> new RuntimeException("ROLE_PATIENT not found"));

        user.getPermissions().add(patientRole);

        Patient patient = new Patient();
        patient.setUser(user);
        patient.setMedicalCardNumber(dto.getMedicalCardNumberDto());

        return patientMapper.toDto(patientRepository.save(patient));
    }

    @Override
    public PatientDto getById(Long id) {
        return patientMapper.toDto(
                patientRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Patient not found"))
        );
    }

    @Override
    public List<PatientDto> getAll() {
        return patientMapper.toDtoList(patientRepository.findAll());
    }

    @Override
    @Transactional
    public PatientDto update(Long id, PatientDto dto) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        patient.setMedicalCardNumber(dto.getMedicalCardNumberDto());
        return patientMapper.toDto(patientRepository.save(patient));
    }

    @Override
    public boolean delete(Long id) {
        if (!patientRepository.existsById(id)) return false;
        patientRepository.deleteById(id);
        return true;
    }
}
