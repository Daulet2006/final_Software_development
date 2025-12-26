package awithd.finalproject.controller;

import awithd.finalproject.dto.UserDto;
import awithd.finalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto dto) {
        service.register(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMe() {
        return new ResponseEntity<>(service.getMe(),HttpStatus.OK);
    }

}
