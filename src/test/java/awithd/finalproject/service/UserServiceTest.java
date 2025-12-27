package awithd.finalproject.service;

import awithd.finalproject.dto.UserDto;
import awithd.finalproject.entity.Permission;
import awithd.finalproject.entity.User;
import awithd.finalproject.repository.PermissionRepository;
import awithd.finalproject.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void registerTest() {
        String email = "test" + UUID.randomUUID().toString().substring(0, 8) + "@test.com";
        UserDto dto = UserDto.builder()
                .emailDto(email)
                .passwordDto("12345")
                .firstNameDto("Test")
                .lastNameDto("User")
                .build();

        userService.register(dto);

        User user = userRepository.findByEmail(email).orElse(null);

        Assertions.assertNotNull(user);
        Assertions.assertEquals(dto.getEmailDto(), user.getEmail());
        Assertions.assertEquals("Test", user.getFirstName());
        Assertions.assertEquals("User", user.getLastName());
    }

    @Test
    void getMeTest() {
        Permission role = permissionRepository.findByName("ROLE_USER").orElseThrow();

        User user = new User();
        user.setEmail("me@mail.com");
        user.setPassword(passwordEncoder.encode("111"));
        user.setFirstName("Current");
        user.setLastName("User");
        user.setPermissions(Set.of(role));
        userRepository.save(user);

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                "me@mail.com",
                null,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))  
        );


        SecurityContextHolder.getContext().setAuthentication(auth);

        SecurityContextHolder.getContext().setAuthentication(auth);

        UserDto me = userService.getMe();

        Assertions.assertNotNull(me);
        Assertions.assertEquals(user.getEmail(), me.getEmailDto());
        Assertions.assertEquals(user.getFirstName(), me.getFirstNameDto());
        Assertions.assertEquals(user.getLastName(), me.getLastNameDto());
    }
}