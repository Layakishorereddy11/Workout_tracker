package com.workouttracker.api.repositories;

import com.workouttracker.api.models.Template;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemplateRepository extends JpaRepository<Template, Long> {
    List<Template> findByUserId(Long userId);
    void deleteByIdAndUserId(Long id, Long userId);
} 