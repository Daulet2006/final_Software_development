package awithd.finalproject.mapper;

import awithd.finalproject.dto.PatientDto;
import awithd.finalproject.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    @Mapping(target = "medicalCardNumberDto", source = "medicalCardNumber")
    @Mapping(target = "emailDto", source = "user.email")
    @Mapping(target = "firstNameDto", source = "user.firstName")
    @Mapping(target = "lastNameDto", source = "user.lastName")
    @Mapping(target = "appointmentDtos", ignore = true)
    @Mapping(target = "doctorDtos", ignore = true)
    PatientDto toDto(Patient patient);

    @Mapping(target = "medicalCardNumber", source = "medicalCardNumberDto")
    @Mapping(target = "appointments", ignore = true)
    @Mapping(target = "doctors", ignore = true)
    Patient toEntity(PatientDto dto);

    List<PatientDto> toDtoList(List<Patient> patients);
}

