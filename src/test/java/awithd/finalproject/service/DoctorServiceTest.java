package awithd.finalproject.service;

import awithd.finalproject.dto.DoctorDto;
import awithd.finalproject.entity.User;
import awithd.finalproject.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class DoctorServiceTest {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void getAllDoctorsTest() {
        List<DoctorDto> doctorDtoList = doctorService.getAll();

        Assertions.assertNotNull(doctorDtoList);

        if (!doctorDtoList.isEmpty()) {
            for (DoctorDto doctorDto : doctorDtoList) {
                Assertions.assertNotNull(doctorDto);
                Assertions.assertNotNull(doctorDto.getId());
                Assertions.assertNotNull(doctorDto.getEmailDto());
                Assertions.assertNotNull(doctorDto.getFirstNameDto());
                Assertions.assertNotNull(doctorDto.getLastNameDto());
                Assertions.assertNotNull(doctorDto.getSpecializationDto());
                Assertions.assertNotNull(doctorDto.getYearsOfExperienceDto());
            }
        }
    }

    @Test
    void getDoctorByIdTest() {
        User user = new User();
        user.setEmail("doctor12@mail.com");
        user.setPassword("$2a$12$QvDiehO1CgbXuMnh.DOv/.ij/O5Q5Cz1wSw/u7xc2lsSH7dJWhMLy");
        user.setFirstName("Test");
        user.setLastName("Doctor");

        userRepository.save(user);

        DoctorDto dto = new DoctorDto();
        dto.setId(user.getId());
        dto.setSpecializationDto("Cardiologist");
        dto.setYearsOfExperienceDto(5);

        DoctorDto createdDoctor = doctorService.create(dto);

        DoctorDto doctorDto = doctorService.getById(createdDoctor.getId());

        Assertions.assertNotNull(doctorDto);
        Assertions.assertNotNull(doctorDto.getId());
        Assertions.assertNotNull(doctorDto.getEmailDto());
        Assertions.assertNotNull(doctorDto.getFirstNameDto());
        Assertions.assertNotNull(doctorDto.getLastNameDto());
        Assertions.assertNotNull(doctorDto.getSpecializationDto());
        Assertions.assertNotNull(doctorDto.getYearsOfExperienceDto());
    }

    @Test
    void createDoctorTest() {
        User user = new User();
        user.setEmail("doctor123@mail.com");
        user.setPassword("$2a$12$QvDiehO1CgbXuMnh.DOv/.ij/O5Q5Cz1wSw/u7xc2lsSH7dJWhMLy");
        user.setFirstName("Test");
        user.setLastName("Doctor");

        userRepository.save(user);

        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(user.getId());
        doctorDto.setSpecializationDto("Surgeon");
        doctorDto.setYearsOfExperienceDto(10);

        DoctorDto createdDoctor = doctorService.create(doctorDto);

        Assertions.assertNotNull(createdDoctor);
        Assertions.assertNotNull(createdDoctor.getId());

        Assertions.assertEquals(user.getEmail(), createdDoctor.getEmailDto());
        Assertions.assertEquals(user.getFirstName(), createdDoctor.getFirstNameDto());
        Assertions.assertEquals(user.getLastName(), createdDoctor.getLastNameDto());
        Assertions.assertEquals("Surgeon", createdDoctor.getSpecializationDto());
        Assertions.assertEquals(10, createdDoctor.getYearsOfExperienceDto());
    }

    @Test
    void updateDoctorTest() {
        User user = new User();
        user.setEmail("doctor1234@mail.com");
        user.setPassword("$2a$12$QvDiehO1CgbXuMnh.DOv/.ij/O5Q5Cz1wSw/u7xc2lsSH7dJWhMLy");
        user.setFirstName("Test");
        user.setLastName("Doctor");

        userRepository.save(user);

        DoctorDto dto = new DoctorDto();
        dto.setId(user.getId());
        dto.setSpecializationDto("Therapist");
        dto.setYearsOfExperienceDto(3);

        DoctorDto createdDoctor = doctorService.create(dto);
        DoctorDto updateDto = new DoctorDto();
        updateDto.setSpecializationDto("Neurologist");
        updateDto.setYearsOfExperienceDto(8);

        DoctorDto updatedDoctor =
                doctorService.update(createdDoctor.getId(), updateDto);

        Assertions.assertNotNull(updatedDoctor);
        Assertions.assertEquals("Neurologist", updatedDoctor.getSpecializationDto());
        Assertions.assertEquals(8, updatedDoctor.getYearsOfExperienceDto());

        DoctorDto checkDoctor =
                doctorService.getById(createdDoctor.getId());

        Assertions.assertEquals(updatedDoctor.getSpecializationDto(),
                checkDoctor.getSpecializationDto());
        Assertions.assertEquals(updatedDoctor.getYearsOfExperienceDto(),
                checkDoctor.getYearsOfExperienceDto());
    }

    @Test
    void deleteDoctorTest() {
        User user = new User();
        user.setEmail("doctor12345@mail.com");
        user.setPassword("$2a$12$QvDiehO1CgbXuMnh.DOv/.ij/O5Q5Cz1wSw/u7xc2lsSH7dJWhMLy");
        user.setFirstName("Test");
        user.setLastName("Doctor");

        userRepository.save(user);

        DoctorDto dto = new DoctorDto();
        dto.setId(user.getId());
        dto.setSpecializationDto("Dentist");
        dto.setYearsOfExperienceDto(4);

        DoctorDto createdDoctor = doctorService.create(dto);

        boolean deleted =
                doctorService.delete(createdDoctor.getId());

        Assertions.assertTrue(deleted);

        Assertions.assertThrows(RuntimeException.class, () ->
                doctorService.getById(createdDoctor.getId()));
    }
}