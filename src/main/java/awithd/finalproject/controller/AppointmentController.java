package awithd.finalproject.controller;

import awithd.finalproject.dto.AppointmentDto;
import awithd.finalproject.dto.PatientDto;
import awithd.finalproject.service.AppointmentService;
import awithd.finalproject.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody AppointmentDto appointmentDto) {
        return new ResponseEntity<>(appointmentService.create(appointmentDto), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(appointmentService.getAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(appointmentService.getById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody AppointmentDto appointmentDto) {
        return new ResponseEntity<>(appointmentService.update(id, appointmentDto), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return new ResponseEntity<>(appointmentService.delete(id), HttpStatus.OK);
    }
}

