package com.workouttracker.api.repositories;

import com.workouttracker.api.models.TemplateExercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateExerciseRepository extends JpaRepository<TemplateExercise, Long> {
} 