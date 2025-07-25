package com.workouttracker.api.mappers;

import com.workouttracker.api.dto.UserDto;
import com.workouttracker.api.dto.UserRegistrationDto;
import com.workouttracker.api.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toUserDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    User toUser(UserRegistrationDto userRegistrationDto);
} 