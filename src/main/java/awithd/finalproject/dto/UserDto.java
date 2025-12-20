package awithd.finalproject.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;

    private String email;
    private String password;

    private String firstName;
    private String lastName;

    private Set<String> permissions;
}
