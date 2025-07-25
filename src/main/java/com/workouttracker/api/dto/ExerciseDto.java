package com.workouttracker.api.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ExerciseDto {
    private Long id;
    private String exerciseName;
    private int sets;
    private int reps;
    private BigDecimal weightKg;
} 