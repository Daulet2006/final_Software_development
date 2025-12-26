package awithd.finalproject.mapper;

import awithd.finalproject.dto.PatientDto;
import awithd.finalproject.entity.Patient;
import awithd.finalproject.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PatientMapperTest {

    @Autowired
    private PatientMapper mapper;

    @Test
    void toEntityTest() {
        PatientDto dto = PatientDto.builder()
                .id(10L)
                .medicalCardNumberDto("123456")
                .build();

        Patient entity = mapper.toEntity(dto);

        Assertions.assertNotNull(entity);

        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getMedicalCardNumberDto());

        Assertions.assertEquals(dto.getMedicalCardNumberDto(), entity.getMedicalCardNumber());
    }

    @Test
    void toDtoTest() {
        User user = new User();
        user.setId(10L);
        user.setEmail("patient@test.com");
        user.setFirstName("Anna");
        user.setLastName("Ivanova");

        Patient entity = new Patient();
        entity.setId(10L);
        entity.setMedicalCardNumber("123456");
        entity.setUser(user);

        PatientDto dto = mapper.toDto(entity);

        Assertions.assertNotNull(dto);

        Assertions.assertNotNull(entity.getId());
        Assertions.assertNotNull(entity.getMedicalCardNumber());

        Assertions.assertEquals(entity.getId(), dto.getId());
        Assertions.assertEquals(entity.getMedicalCardNumber(), dto.getMedicalCardNumberDto());
        Assertions.assertEquals(user.getEmail(), dto.getEmailDto());
        Assertions.assertEquals(user.getFirstName(), dto.getFirstNameDto());
        Assertions.assertEquals(user.getLastName(), dto.getLastNameDto());
    }

    @Test
    void toDtoListTest() {
        Patient p1 = new Patient();
        p1.setId(1L);
        p1.setMedicalCardNumber("111");

        Patient p2 = new Patient();
        p2.setId(2L);
        p2.setMedicalCardNumber("222");

        Patient p3 = new Patient();
        p3.setId(3L);
        p3.setMedicalCardNumber("333");

        List<Patient> entities = List.of(p1, p2, p3);

        List<PatientDto> dtos = mapper.toDtoList(entities);

        Assertions.assertNotNull(dtos);
        Assertions.assertEquals(entities.size(), dtos.size());

        for (int i = 0; i < entities.size(); i++) {
            Patient entity = entities.get(i);
            PatientDto dto = dtos.get(i);

            Assertions.assertNotNull(dto);

            Assertions.assertEquals(entity.getId(), dto.getId());
            Assertions.assertEquals(entity.getMedicalCardNumber(), dto.getMedicalCardNumberDto());
        }
    }
}

