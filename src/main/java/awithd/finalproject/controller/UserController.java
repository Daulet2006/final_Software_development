package awithd.finalproject.controller;

import awithd.finalproject.dto.LoginRequest;
import awithd.finalproject.dto.UserDto;
import awithd.finalproject.entity.User;
import awithd.finalproject.service.AuthService;
import awithd.finalproject.service.JwtService;
import awithd.finalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {
    private final AuthService authService;
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody UserDto dto) {
        User user = userService.register(dto);
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        String token = authService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(Map.of("token", token));
    }
}
