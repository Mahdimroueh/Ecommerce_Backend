package com.mroueh.mapper;

import com.mroueh.dto.SizeVariationDto;
import com.mroueh.dto.UserDto;
import com.mroueh.entity.SizeVariation;
import com.mroueh.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

        @Mapping(target = "password", ignore = true)
        User toEntity(UserDto userDto);

        UserDto toDto(User user);
}
