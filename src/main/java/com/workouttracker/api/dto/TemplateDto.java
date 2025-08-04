package com.workouttracker.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class TemplateDto {
    private Long id;
    private Long userId;
    private String name;
    private List<ExerciseDto> exercises;
} 