package awithd.finalproject.controller;

import awithd.finalproject.dto.AppointmentDto;
import awithd.finalproject.dto.PatientDto;
import awithd.finalproject.service.AppointmentService;
import awithd.finalproject.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AppointmentDto appointmentDto) {
        return new ResponseEntity<>(appointmentService.create(appointmentDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(appointmentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(appointmentService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AppointmentDto appointmentDto) {
        return new ResponseEntity<>(appointmentService.update(id, appointmentDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return new ResponseEntity<>(appointmentService.delete(id), HttpStatus.OK);
    }
}
