package awithd.finalproject.dto;

import awithd.finalproject.entity.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AppointmentDto {

    private Long id;

    private Long doctorId;
    private Long patientId;

    private LocalDateTime appointmentTime;
    private AppointmentStatus status;
    private String reason;
}
