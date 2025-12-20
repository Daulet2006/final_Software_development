package awithd.finalproject.mapper;

import awithd.finalproject.dto.UserDto;
import awithd.finalproject.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring",uses = {PermissionMapper.class})
public interface UserMapper {

    @Mappings({
            @Mapping(target = "email", source = "emailDto"),
            @Mapping(target = "password", source = "passwordDto"),
            @Mapping(target = "firstName", source = "firstNameDto"),
            @Mapping(target = "lastName", source = "lastNameDto"),
            @Mapping(target = "permissions", source = "permissionsDto")
    })
    User toEntity(UserDto dto);

    @Mappings({
            @Mapping(target = "emailDto", source = "email"),
            @Mapping(target = "passwordDto", ignore = true),
            @Mapping(target = "firstNameDto", source = "firstName"),
            @Mapping(target = "lastNameDto", source = "lastName"),
            @Mapping(target = "permissionsDto", source = "permissions")
    })
    UserDto toDto(User user);
}
