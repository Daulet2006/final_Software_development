package awithd.finalproject.mapper;

import awithd.finalproject.dto.PermissionDto;
import awithd.finalproject.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    @Mapping(target = "name",source = "nameDto")
    Permission toEntity(PermissionDto dto);
    @Mapping(target = "nameDto",source = "name")
    PermissionDto toDto(Permission entity);
}

