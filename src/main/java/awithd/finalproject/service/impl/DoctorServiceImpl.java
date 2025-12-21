package awithd.finalproject.service.impl;

import awithd.finalproject.dto.DoctorDto;
import awithd.finalproject.entity.Doctor;
import awithd.finalproject.mapper.DoctorMapper;
import awithd.finalproject.repository.DoctorRepository;
import awithd.finalproject.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    @Override
    public DoctorDto create(DoctorDto doctorDto) {
        return doctorMapper.toDto(doctorRepository.save(doctorMapper.toEntity(doctorDto)));
    }

    @Override
    public DoctorDto getById(Long id) {
        return doctorMapper.toDto(doctorRepository.findById(id).orElse(null));
    }

    @Override
    public List<DoctorDto> getAll() {
        return doctorMapper.toDtoList(doctorRepository.findAll());
    }

    @Override
    public DoctorDto update(Long id, DoctorDto doctorDto) {
        Doctor newEntity = doctorMapper.toEntity(doctorDto);
        Doctor oldEntity = doctorRepository.findById(id).orElseThrow();

        oldEntity.setEmail(newEntity.getEmail());
        oldEntity.setFirstName(newEntity.getFirstName());
        oldEntity.setLastName(newEntity.getLastName());
        oldEntity.setSpecialization(newEntity.getSpecialization());
        oldEntity.setYearsOfExperience(newEntity.getYearsOfExperience());

        return doctorMapper.toDto(doctorRepository.save(oldEntity));
    }

    @Override
    public boolean delete(Long id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
