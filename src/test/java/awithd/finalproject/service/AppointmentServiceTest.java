package awithd.finalproject.service;

import awithd.finalproject.dto.AppointmentDto;
import awithd.finalproject.dto.DoctorDto;
import awithd.finalproject.dto.PatientDto;
import awithd.finalproject.entity.User;
import awithd.finalproject.entity.AppointmentStatus;
import awithd.finalproject.repository.AppointmentRepository;
import awithd.finalproject.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@SpringBootTest
@ActiveProfiles("test")
public class AppointmentServiceTest {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void getAllAppointmentsTest() {
        List<AppointmentDto> appointmentDtoList = appointmentService.getAll();

        Assertions.assertNotNull(appointmentDtoList);

        if (!appointmentDtoList.isEmpty()) {
            for (AppointmentDto dto : appointmentDtoList) {
                Assertions.assertNotNull(dto);
                Assertions.assertNotNull(dto.getId());
                Assertions.assertNotNull(dto.getDoctorId());
                Assertions.assertNotNull(dto.getPatientId());
                Assertions.assertNotNull(dto.getAppointmentTimeDto());
                Assertions.assertNotNull(dto.getStatusDto());
            }
        }
    }

    @Test
    void getAppointmentByIdTest() {
        AppointmentDto created = createTestAppointment();

        AppointmentDto appointmentDto = appointmentService.getById(created.getId());

        Assertions.assertNotNull(appointmentDto);
        Assertions.assertNotNull(appointmentDto.getId());
        Assertions.assertNotNull(appointmentDto.getDoctorId());
        Assertions.assertNotNull(appointmentDto.getPatientId());
        Assertions.assertNotNull(appointmentDto.getAppointmentTimeDto());
        Assertions.assertNotNull(appointmentDto.getStatusDto());
    }

    @Test
    void createAppointmentTest() {
        AppointmentDto appointmentDto = createTestAppointment();

        Assertions.assertNotNull(appointmentDto);
        Assertions.assertNotNull(appointmentDto.getId());
        Assertions.assertNotNull(appointmentDto.getDoctorId());
        Assertions.assertNotNull(appointmentDto.getPatientId());
        Assertions.assertNotNull(appointmentDto.getAppointmentTimeDto());
        Assertions.assertEquals(AppointmentStatus.CREATED, appointmentDto.getStatusDto());
        Assertions.assertEquals("Zabolel", appointmentDto.getReasonDto());
    }

    @Test
    void updateAppointmentTest() {
        AppointmentDto created = createTestAppointment();

        AppointmentDto updateDto = new AppointmentDto();
        updateDto.setReasonDto("Updated reason");
        updateDto.setStatusDto(AppointmentStatus.COMPLETED);

        AppointmentDto updated = appointmentService.update(created.getId(), updateDto);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals("Updated reason", updated.getReasonDto());
        Assertions.assertEquals(AppointmentStatus.COMPLETED, updated.getStatusDto());

        AppointmentDto check = appointmentService.getById(created.getId());

        Assertions.assertEquals(updated.getReasonDto(), check.getReasonDto());
        Assertions.assertEquals(updated.getStatusDto(), check.getStatusDto());
    }

    @Test
    void deleteAppointmentTest() {
        AppointmentDto created = createTestAppointment();

        boolean deleted = appointmentService.delete(created.getId());

        Assertions.assertTrue(deleted);
    }

    private AppointmentDto createTestAppointment() {
        Random random = new Random();
        int randomNum = random.nextInt(10);

        User doctorUser = new User();
        doctorUser.setEmail("doctor" + randomNum + "@gmail.com");
        doctorUser.setPassword("$2a$12$QvDiehO1CgbXuMnh.DOv/.ij/O5Q5Cz1wSw/u7xc2lsSH7dJWhMLy");
        doctorUser.setFirstName("Doc");
        doctorUser.setLastName("Tor");

        userRepository.save(doctorUser);

        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(doctorUser.getId());
        doctorDto.setSpecializationDto("Therapist");
        doctorDto.setYearsOfExperienceDto(5);

        DoctorDto doctor = doctorService.create(doctorDto);

        User patientUser = new User();
        patientUser.setEmail("patient" + randomNum + "@mail.com");
        patientUser.setPassword("$2a$12$QvDiehO1CgbXuMnh.DOv/.ij/O5Q5Cz1wSw/u7xc2lsSH7dJWhMLy");
        patientUser.setFirstName("Pat");
        patientUser.setLastName("Ient");

        userRepository.save(patientUser);

        PatientDto patientDto = new PatientDto();
        patientDto.setId(patientUser.getId());
        patientDto.setMedicalCardNumberDto("777");

        PatientDto patient = patientService.create(patientDto);

        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setDoctorId(doctor.getId());
        appointmentDto.setPatientId(patient.getId());
        appointmentDto.setAppointmentTimeDto(LocalDateTime.now());
        appointmentDto.setReasonDto("Zabolel");

        return appointmentService.create(appointmentDto);
    }
}
