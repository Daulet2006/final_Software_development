package awithd.finalproject.mapper;

import awithd.finalproject.dto.DoctorDto;
import awithd.finalproject.entity.Doctor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DoctorMapperTest {

    @Autowired
    private DoctorMapper mapper;

    @Test
    void toEntityTest() {
        DoctorDto dto = DoctorDto.builder()
                .id(10L)
                .emailDto("doctor@test.com")
                .firstNameDto("John")
                .lastNameDto("Doe")
                .specializationDto("Cardiologist")
                .yearsOfExperienceDto(15)
                .build();

        Doctor entity = mapper.toEntity(dto);

        Assertions.assertNotNull(entity);

        Assertions.assertEquals(dto.getEmailDto(), entity.getEmail());
        Assertions.assertEquals(dto.getFirstNameDto(), entity.getFirstName());
        Assertions.assertEquals(dto.getLastNameDto(), entity.getLastName());
        Assertions.assertEquals(dto.getSpecializationDto(), entity.getSpecialization());
        Assertions.assertEquals(dto.getYearsOfExperienceDto(), entity.getYearsOfExperience());
    }

    @Test
    void toDtoTest() {
        Doctor entity = new Doctor();
        entity.setId(10L);
        entity.setEmail("doctor@test.com");
        entity.setFirstName("John");
        entity.setLastName("Doe");
        entity.setSpecialization("Cardiologist");
        entity.setYearsOfExperience(15);

        DoctorDto dto = mapper.toDto(entity);

        Assertions.assertNotNull(dto);

        Assertions.assertEquals(entity.getId(), dto.getId());
        Assertions.assertEquals(entity.getEmail(), dto.getEmailDto());
        Assertions.assertEquals(entity.getFirstName(), dto.getFirstNameDto());
        Assertions.assertEquals(entity.getLastName(), dto.getLastNameDto());
        Assertions.assertEquals(entity.getSpecialization(), dto.getSpecializationDto());
        Assertions.assertEquals(entity.getYearsOfExperience(), dto.getYearsOfExperienceDto());
    }

    @Test
    void toDtoListTest() {
        Doctor d1 = new Doctor();
        d1.setId(1L);
        d1.setEmail("d1@test.com");
        d1.setFirstName("Alice");
        d1.setLastName("Brown");
        d1.setSpecialization("Therapist");
        d1.setYearsOfExperience(5);

        Doctor d2 = new Doctor();
        d2.setId(2L);
        d2.setEmail("d2@test.com");
        d2.setFirstName("Bob");
        d2.setLastName("Smith");
        d2.setSpecialization("Surgeon");
        d2.setYearsOfExperience(12);

        Doctor d3 = new Doctor();
        d3.setId(3L);
        d3.setEmail("d3@test.com");
        d3.setFirstName("Clara");
        d3.setLastName("Johnson");
        d3.setSpecialization("Pediatrician");
        d3.setYearsOfExperience(8);

        List<Doctor> entities = List.of(d1, d2, d3);

        List<DoctorDto> dtos = mapper.toDtoList(entities);

        Assertions.assertNotNull(dtos);
        Assertions.assertEquals(entities.size(), dtos.size());

        for (int i = 0; i < entities.size(); i++) {
            Doctor entity = entities.get(i);
            DoctorDto dto = dtos.get(i);

            Assertions.assertNotNull(dto);

            Assertions.assertEquals(entity.getId(), dto.getId());
            Assertions.assertEquals(entity.getEmail(), dto.getEmailDto());
            Assertions.assertEquals(entity.getFirstName(), dto.getFirstNameDto());
            Assertions.assertEquals(entity.getLastName(), dto.getLastNameDto());
            Assertions.assertEquals(entity.getSpecialization(), dto.getSpecializationDto());
            Assertions.assertEquals(entity.getYearsOfExperience(), dto.getYearsOfExperienceDto());
        }
    }
}
