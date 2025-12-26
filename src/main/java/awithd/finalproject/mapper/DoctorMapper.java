package awithd.finalproject.mapper;

import awithd.finalproject.dto.DoctorDto;
import awithd.finalproject.entity.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    @Mapping(target = "emailDto", source = "user.email")
    @Mapping(target = "firstNameDto", source = "user.firstName")
    @Mapping(target = "lastNameDto", source = "user.lastName")
    @Mapping(target = "specializationDto", source = "specialization")
    @Mapping(target = "yearsOfExperienceDto", source = "yearsOfExperience")
    @Mapping(target = "appointmentDtos", ignore = true)
    @Mapping(target = "patientDtos", ignore = true)
    DoctorDto toDto(Doctor doctor);

    @Mapping(target = "specialization", source = "specializationDto")
    @Mapping(target = "yearsOfExperience", source = "yearsOfExperienceDto")
    @Mapping(target = "appointments", ignore = true)
    @Mapping(target = "patients", ignore = true)
    Doctor toEntity(DoctorDto dto);

    List<DoctorDto> toDtoList(List<Doctor> doctors);
}

