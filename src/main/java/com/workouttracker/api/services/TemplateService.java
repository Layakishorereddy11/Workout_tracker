package com.workouttracker.api.services;

import com.workouttracker.api.dto.TemplateDto;
import com.workouttracker.api.mappers.TemplateMapper;
import com.workouttracker.api.models.Template;
import com.workouttracker.api.repositories.TemplateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TemplateService {

    private final TemplateRepository templateRepository;
    private final TemplateMapper templateMapper;

    public TemplateService(TemplateRepository templateRepository, TemplateMapper templateMapper) {
        this.templateRepository = templateRepository;
        this.templateMapper = templateMapper;
    }

    public List<TemplateDto> getTemplatesByUserId(Long userId) {
        return templateRepository.findByUserId(userId).stream()
                .map(templateMapper::toDto)
                .collect(Collectors.toList());
    }

    public TemplateDto createTemplate(Long userId, TemplateDto templateDto) {
        templateDto.setUserId(userId);
        Template template = templateMapper.toEntity(templateDto);
        template.getExercises().forEach(exercise -> exercise.setTemplate(template));
        Template savedTemplate = templateRepository.save(template);
        return templateMapper.toDto(savedTemplate);
    }

    @Transactional
    public void deleteTemplate(Long userId, Long templateId) {
        templateRepository.deleteByIdAndUserId(templateId, userId);
    }
} 