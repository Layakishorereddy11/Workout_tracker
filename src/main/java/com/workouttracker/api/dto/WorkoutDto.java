package com.workouttracker.api.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class WorkoutDto {
    private Long id;
    private Long userId;
    private LocalDate workoutDate;
    private String notes;
    private List<ExerciseDto> exercises;
} 