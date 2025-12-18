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
    private String specializationDto;
    private Integer yearsOfExperienceDto;

    private String firstNameDto;
    private String lastNameDto;
    private String emailDto;

    private Set<Long> appointmentIds;
    private Set<Long> patientIds;
}
