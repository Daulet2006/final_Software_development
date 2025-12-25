package awithd.finalproject.mapper;

import awithd.finalproject.dto.AppointmentDto;
import awithd.finalproject.entity.Appointment;
import awithd.finalproject.entity.AppointmentStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class AppointmentMapperTest {
    @Autowired
    private AppointmentMapper mapper;

    @Test
    void toEntityTest(){
        AppointmentDto dto = AppointmentDto.builder()
                .id(1L)
                .appointmentTimeDto(LocalDateTime.of(2025, 1, 15, 10, 30))
                .statusDto(AppointmentStatus.CREATED)
                .reasonDto("Annual medical checkup")
                .build();
        Appointment entity = mapper.toEntity(dto);
        Assertions.assertNotNull(entity);
        Assertions.assertEquals(dto.getId(),entity.getId());
        Assertions.assertEquals(dto.getStatusDto(),entity.getStatus());
        Assertions.assertEquals(dto.getAppointmentTimeDto(),entity.getAppointmentTime());
        Assertions.assertEquals(dto.getReasonDto(),entity.getReason());
    }
    @Test
    void toDtoTest(){
        Appointment entity = new Appointment();
        entity.setId(1L);
        entity.setAppointmentTime(LocalDateTime.of(2025, 1, 15, 10, 30));
        entity.setStatus(AppointmentStatus.CREATED);
        entity.setReason("Annual medical checkup");

        AppointmentDto dto = mapper.toDto(entity);
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(dto.getId(),entity.getId());
        Assertions.assertEquals(dto.getStatusDto(),entity.getStatus());
        Assertions.assertEquals(dto.getAppointmentTimeDto(),entity.getAppointmentTime());
        Assertions.assertEquals(dto.getReasonDto(),entity.getReason());
    }
    @Test
    void toDtoListTest(){
        Appointment entity1 = new Appointment();
        entity1.setId(1L);
        entity1.setAppointmentTime(LocalDateTime.of(2025, 1, 15, 10, 30));
        entity1.setStatus(AppointmentStatus.CREATED);
        entity1.setReason("Annual medical checkup");
        Appointment entity2 = new Appointment();
        entity2.setId(1L);
        entity2.setAppointmentTime(LocalDateTime.of(2025, 1, 15, 10, 30));
        entity2.setStatus(AppointmentStatus.CREATED);
        entity2.setReason("Annual medical checkup");
        Appointment entity3 = new Appointment();
        entity3.setId(1L);
        entity3.setAppointmentTime(LocalDateTime.of(2025, 1, 15, 10, 30));
        entity3.setStatus(AppointmentStatus.CREATED);
        entity3.setReason("Annual medical checkup");
        List<Appointment> entities = List.of(entity1,entity2,entity3);
        List<AppointmentDto> dtos = mapper.toDtoList(entities);
        Assertions.assertNotNull(dtos);
        Assertions.assertEquals(entities.size(), dtos.size());
        for (int i = 0; i < entities.size(); i++) {

            Appointment entity = entities.get(i);
            AppointmentDto dto = dtos.get(i);

            Assertions.assertNotNull(dto);

            Assertions.assertEquals(entity.getId(), dto.getId());
            Assertions.assertEquals(dto.getStatusDto(),entity.getStatus());
            Assertions.assertEquals(dto.getAppointmentTimeDto(),entity.getAppointmentTime());
            Assertions.assertEquals(dto.getReasonDto(),entity.getReason());
        }
    }
}
