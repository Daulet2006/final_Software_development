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

    private String emailDto;
    private String passwordDto;

    private String firstNameDto;
    private String lastNameDto;

    private Set<PermissionDto> permissionsDto;
}
