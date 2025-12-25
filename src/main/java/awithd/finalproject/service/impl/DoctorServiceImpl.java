package awithd.finalproject.service.impl;

import awithd.finalproject.dto.DoctorDto;
import awithd.finalproject.entity.Doctor;
import awithd.finalproject.entity.Permission;
import awithd.finalproject.entity.User;
import awithd.finalproject.mapper.DoctorMapper;
import awithd.finalproject.repository.DoctorRepository;
import awithd.finalproject.repository.PermissionRepository;
import awithd.finalproject.repository.UserRepository;
import awithd.finalproject.service.DoctorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;

    @Transactional
    @Override
    public DoctorDto create(DoctorDto dto) {

        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Permission doctorRole = permissionRepository
                .findByName("ROLE_DOCTOR")
                .orElseThrow();

        user.getPermissions().add(doctorRole);

        Doctor doctor = new Doctor();
        doctor.setId(user.getId());
        doctor.setEmail(user.getEmail());
        doctor.setPassword(user.getPassword());
        doctor.setFirstName(user.getFirstName());
        doctor.setLastName(user.getLastName());
        doctor.setSpecialization(dto.getSpecializationDto());
        doctor.setYearsOfExperience(dto.getYearsOfExperienceDto());

        return doctorMapper.toDto(doctorRepository.save(doctor));
    }

    @Override
    public DoctorDto getById(Long id) {
        return doctorMapper.toDto(
                doctorRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Doctor not found"))
        );
    }

    @Override
    public List<DoctorDto> getAll() {
        return doctorMapper.toDtoList(doctorRepository.findAll());
    }

    @Override
    @Transactional
    public DoctorDto update(Long id, DoctorDto dto) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        doctor.setSpecialization(dto.getSpecializationDto());
        doctor.setYearsOfExperience(dto.getYearsOfExperienceDto());

        return doctorMapper.toDto(doctorRepository.save(doctor));
    }

    @Override
    public boolean delete(Long id) {
        if (!doctorRepository.existsById(id)) return false;
        doctorRepository.deleteById(id);
        return true;
    }
}
