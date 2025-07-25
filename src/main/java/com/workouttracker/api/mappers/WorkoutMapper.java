package com.workouttracker.api.mappers;

import com.workouttracker.api.dto.WorkoutDto;
import com.workouttracker.api.models.Workout;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {ExerciseMapper.class})
public interface WorkoutMapper {

    WorkoutMapper INSTANCE = Mappers.getMapper(WorkoutMapper.class);

    @Mapping(source = "user.id", target = "userId")
    WorkoutDto toWorkoutDto(Workout workout);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Workout toWorkout(WorkoutDto workoutDto);
} 