package com.workouttracker.api.mappers;

import com.workouttracker.api.dto.ExerciseDto;
import com.workouttracker.api.models.Exercise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {

    ExerciseMapper INSTANCE = Mappers.getMapper(ExerciseMapper.class);

    ExerciseDto toExerciseDto(Exercise exercise);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "workout", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Exercise toExercise(ExerciseDto exerciseDto);
} 