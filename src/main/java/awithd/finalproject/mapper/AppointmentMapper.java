package awithd.finalproject.mapper;

import awithd.finalproject.dto.AppointmentDto;
import awithd.finalproject.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "appointmentTime", source = "appointmentTimeDto")
    @Mapping(target = "status", source = "statusDto")
    @Mapping(target = "reason", source = "reasonDto")
    Appointment toEntity(AppointmentDto dto);

    @Mapping(target = "doctorId", ignore = true)
    @Mapping(target = "patientId", ignore = true)
    @Mapping(target = "appointmentTimeDto", source = "appointmentTime")
    @Mapping(target = "statusDto", source = "status")
    @Mapping(target = "reasonDto", source = "reason")
    AppointmentDto toDto(Appointment entity);

    List<AppointmentDto> toDtoList(List<Appointment> appointmentList);
}
