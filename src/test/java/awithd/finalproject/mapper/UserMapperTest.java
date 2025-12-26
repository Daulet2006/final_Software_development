package awithd.finalproject.mapper;

import awithd.finalproject.dto.UserDto;
import awithd.finalproject.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper mapper;

    @Test
    void toEntityTest() {
        UserDto dto = UserDto.builder()
                .id(10L)
                .emailDto("user@gmail.com")
                .passwordDto("12345")
                .firstNameDto("User")
                .lastNameDto("User")
                .build();

        User entity = mapper.toEntity(dto);

        Assertions.assertNotNull(entity);

        Assertions.assertNotNull(dto.getEmailDto());
        Assertions.assertNotNull(dto.getPasswordDto());
        Assertions.assertNotNull(dto.getFirstNameDto());
        Assertions.assertNotNull(dto.getLastNameDto());

        Assertions.assertEquals(dto.getEmailDto(), entity.getEmail());
        Assertions.assertEquals(dto.getPasswordDto(), entity.getPassword());
        Assertions.assertEquals(dto.getFirstNameDto(), entity.getFirstName());
        Assertions.assertEquals(dto.getLastNameDto(), entity.getLastName());
    }

    @Test
    void toDtoTest() {
        User entity = new User();
        entity.setId(10L);
        entity.setEmail("user@gmail.com");
        entity.setPassword("12345");
        entity.setFirstName("User");
        entity.setLastName("User");

        UserDto dto = mapper.toDto(entity);

        Assertions.assertNotNull(dto);

        Assertions.assertNotNull(entity.getEmail());
        Assertions.assertNotNull(entity.getFirstName());
        Assertions.assertNotNull(entity.getLastName());

        Assertions.assertEquals(entity.getEmail(), dto.getEmailDto());
        Assertions.assertEquals(entity.getFirstName(), dto.getFirstNameDto());
        Assertions.assertEquals(entity.getLastName(), dto.getLastNameDto());

        Assertions.assertNull(dto.getPasswordDto());
    }
}
