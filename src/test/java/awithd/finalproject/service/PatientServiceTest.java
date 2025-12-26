package awithd.finalproject.service;

import awithd.finalproject.dto.PatientDto;
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
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void getAllPatientsTest() {
        List<PatientDto> patientDtoList = patientService.getAll();

        Assertions.assertNotNull(patientDtoList);

        if (!patientDtoList.isEmpty()) {
            for (PatientDto patientDto : patientDtoList) {
                Assertions.assertNotNull(patientDto);
                Assertions.assertNotNull(patientDto.getId());
                Assertions.assertNotNull(patientDto.getEmailDto());
                Assertions.assertNotNull(patientDto.getFirstNameDto());
                Assertions.assertNotNull(patientDto.getLastNameDto());
                Assertions.assertNotNull(patientDto.getMedicalCardNumberDto());
            }
        }
    }

    @Test
    void getPatientByIdTest() {
        User user = new User();
        user.setEmail("patientone@gmail.com");
        user.setPassword("$2a$12$QvDiehO1CgbXuMnh.DOv/.ij/O5Q5Cz1wSw/u7xc2lsSH7dJWhMLy");
        user.setFirstName("Test");
        user.setLastName("Patient");

        userRepository.save(user);

        PatientDto dto = new PatientDto();
        dto.setId(user.getId());
        dto.setMedicalCardNumberDto("111");

        PatientDto createdPatient = patientService.create(dto);

        PatientDto patientDto = patientService.getById(createdPatient.getId());

        Assertions.assertNotNull(patientDto);
        Assertions.assertNotNull(patientDto.getId());
        Assertions.assertNotNull(patientDto.getEmailDto());
        Assertions.assertNotNull(patientDto.getFirstNameDto());
        Assertions.assertNotNull(patientDto.getLastNameDto());
        Assertions.assertNotNull(patientDto.getMedicalCardNumberDto());
    }

    @Test
    void createPatientTest() {
        User user = new User();
        user.setEmail("patienttwo@gmail.com");
        user.setPassword("$2a$12$QvDiehO1CgbXuMnh.DOv/.ij/O5Q5Cz1wSw/u7xc2lsSH7dJWhMLy");
        user.setFirstName("Test");
        user.setLastName("Patient");

        userRepository.save(user);

        PatientDto patientDto = new PatientDto();
        patientDto.setId(user.getId());
        patientDto.setMedicalCardNumberDto("222");

        PatientDto createdPatient = patientService.create(patientDto);

        Assertions.assertNotNull(createdPatient);
        Assertions.assertNotNull(createdPatient.getId());

        Assertions.assertEquals(user.getEmail(), createdPatient.getEmailDto());
        Assertions.assertEquals(user.getFirstName(), createdPatient.getFirstNameDto());
        Assertions.assertEquals(user.getLastName(), createdPatient.getLastNameDto());
        Assertions.assertEquals("222", createdPatient.getMedicalCardNumberDto());
    }

    @Test
    void updatePatientTest() {
        User user = new User();
        user.setEmail("patientthree@gmail.com");
        user.setPassword("$2a$12$QvDiehO1CgbXuMnh.DOv/.ij/O5Q5Cz1wSw/u7xc2lsSH7dJWhMLy");
        user.setFirstName("Test");
        user.setLastName("Patient");

        userRepository.save(user);

        PatientDto dto = new PatientDto();
        dto.setId(user.getId());
        dto.setMedicalCardNumberDto("333");

        PatientDto createdPatient = patientService.create(dto);

        PatientDto updateDto = new PatientDto();
        updateDto.setMedicalCardNumberDto("444");

        PatientDto updatedPatient =
                patientService.update(createdPatient.getId(), updateDto);

        Assertions.assertNotNull(updatedPatient);
        Assertions.assertEquals("444", updatedPatient.getMedicalCardNumberDto());

        PatientDto checkPatient =
                patientService.getById(createdPatient.getId());

        Assertions.assertEquals(
                updatedPatient.getMedicalCardNumberDto(),
                checkPatient.getMedicalCardNumberDto()
        );
    }

    @Test
    void deletePatientTest() {
        User user = new User();
        user.setEmail("patientfour@gmail.com");
        user.setPassword("$2a$12$QvDiehO1CgbXuMnh.DOv/.ij/O5Q5Cz1wSw/u7xc2lsSH7dJWhMLy");
        user.setFirstName("Test");
        user.setLastName("Patient");

        userRepository.save(user);

        PatientDto dto = new PatientDto();
        dto.setId(user.getId());
        dto.setMedicalCardNumberDto("555");

        PatientDto createdPatient = patientService.create(dto);

        boolean deleted =
                patientService.delete(createdPatient.getId());

        Assertions.assertTrue(deleted);
    }
}
