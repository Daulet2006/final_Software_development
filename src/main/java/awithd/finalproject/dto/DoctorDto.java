package awithd.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DoctorDto {
    private Long id;
    private String emailDto;
    private String firstNameDto;
    private String lastNameDto;

    private String specializationDto;
    private Integer yearsOfExperienceDto;

    private Set<AppointmentDto> appointmentDtos;
    private Set<PatientDto> patientDtos;
}
