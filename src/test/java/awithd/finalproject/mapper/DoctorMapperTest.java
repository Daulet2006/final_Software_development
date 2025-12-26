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
                .specializationDto("Cardiologist")
                .yearsOfExperienceDto(15)
                .build();

        Doctor entity = mapper.toEntity(dto);

        Assertions.assertNotNull(entity);

        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getSpecializationDto());
        Assertions.assertNotNull(dto.getYearsOfExperienceDto());

        Assertions.assertEquals(dto.getSpecializationDto(), entity.getSpecialization());
        Assertions.assertEquals(dto.getYearsOfExperienceDto(), entity.getYearsOfExperience());
    }

    @Test
    void toDtoTest() {
        Doctor entity = new Doctor();
        entity.setId(10L);
        entity.setSpecialization("Cardiologist");
        entity.setYearsOfExperience(15);

        DoctorDto dto = mapper.toDto(entity);

        Assertions.assertNotNull(dto);

        Assertions.assertNotNull(entity.getId());
        Assertions.assertNotNull(entity.getSpecialization());
        Assertions.assertNotNull(entity.getYearsOfExperience());

        Assertions.assertEquals(entity.getId(), dto.getId());
        Assertions.assertEquals(entity.getSpecialization(), dto.getSpecializationDto());
        Assertions.assertEquals(entity.getYearsOfExperience(), dto.getYearsOfExperienceDto());
    }

    @Test
    void toDtoListTest() {
        Doctor d1 = new Doctor();
        d1.setId(1L);
        d1.setSpecialization("Therapist");
        d1.setYearsOfExperience(5);

        Doctor d2 = new Doctor();
        d2.setId(2L);
        d2.setSpecialization("Surgeon");
        d2.setYearsOfExperience(12);

        Doctor d3 = new Doctor();
        d3.setId(3L);
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
            Assertions.assertEquals(entity.getSpecialization(), dto.getSpecializationDto());
            Assertions.assertEquals(entity.getYearsOfExperience(), dto.getYearsOfExperienceDto());
        }
    }
}
