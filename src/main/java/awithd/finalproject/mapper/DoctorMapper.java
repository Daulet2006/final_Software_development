package awithd.finalproject.mapper;

import awithd.finalproject.dto.DoctorDto;
import awithd.finalproject.entity.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    @Mapping(target = "email", source = "emailDto")
    @Mapping(target = "firstName", source = "firstNameDto")
    @Mapping(target = "lastName", source = "lastNameDto")
    @Mapping(target = "specialization", source = "specializationDto")
    @Mapping(target = "yearsOfExperience", source = "yearsOfExperienceDto")

    @Mapping(target = "appointments", ignore = true)
    @Mapping(target = "patients", ignore = true)
    Doctor toEntity(DoctorDto dto);

    @Mapping(target = "emailDto", source = "email")
    @Mapping(target = "firstNameDto", source = "firstName")
    @Mapping(target = "lastNameDto", source = "lastName")
    @Mapping(target = "specializationDto", source = "specialization")
    @Mapping(target = "yearsOfExperienceDto", source = "yearsOfExperience")

    @Mapping(target = "appointmentDtoSet", ignore = true)
    @Mapping(target = "patientDtoSet", ignore = true)
    DoctorDto toDto(Doctor doctor);

    List<DoctorDto> toDtoList(List<Doctor> doctorList);
}
