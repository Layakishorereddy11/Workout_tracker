package com.workouttracker.api.mappers;

import com.workouttracker.api.dto.ExerciseDto;
import com.workouttracker.api.models.TemplateExercise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TemplateExerciseMapper {

    @Mapping(target = "id", ignore = true)
    TemplateExercise toTemplateExercise(ExerciseDto exerciseDto);

    ExerciseDto toDto(TemplateExercise templateExercise);
} 