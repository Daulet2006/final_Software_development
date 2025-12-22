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
public class PatientDto {
    private Long id;
    private String medicalCardNumberDto;

    private String emailDto;
    private String firstNameDto;
    private String lastNameDto;

    private Set<AppointmentDto> appointmentDtos;
    private Set<DoctorDto> doctorDtos;
}
