package org.example.main.model.dto;

import org.example.main.model.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateDtoFromUser(@MappingTarget User entity,  UserDTO dto);

}
