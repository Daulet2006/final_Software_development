package awithd.finalproject.mapper;

import awithd.finalproject.dto.PatientDto;
import awithd.finalproject.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    @Mapping(target = "email", source = "emailDto")
    @Mapping(target = "firstName", source = "firstNameDto")
    @Mapping(target = "lastName", source = "lastNameDto")
    @Mapping(target = "medicalCardNumber", source = "medicalCardNumberDto")

    @Mapping(target = "appointments", ignore = true)
    @Mapping(target = "doctors", ignore = true)
    Patient toEntity(PatientDto dto);

    @Mapping(target = "emailDto", source = "email")
    @Mapping(target = "firstNameDto", source = "firstName")
    @Mapping(target = "lastNameDto", source = "lastName")
    @Mapping(target = "medicalCardNumberDto", source = "medicalCardNumber")

    @Mapping(target = "appointmentDtos", ignore = true)
    @Mapping(target = "doctorDtos", ignore = true)
    PatientDto toDto(Patient patient);

    List<PatientDto> toDtoList(List<Patient> patientDtoList);
}
