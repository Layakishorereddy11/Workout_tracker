package com.workouttracker.api.controllers;

import com.workouttracker.api.dto.TemplateDto;
import com.workouttracker.api.services.TemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/templates")
public class TemplateController {

    private final TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @GetMapping
    public ResponseEntity<List<TemplateDto>> getTemplates(@PathVariable Long userId) {
        List<TemplateDto> templates = templateService.getTemplatesByUserId(userId);
        return ResponseEntity.ok(templates);
    }

    @PostMapping
    public ResponseEntity<TemplateDto> createTemplate(@PathVariable Long userId, @RequestBody TemplateDto templateDto) {
        TemplateDto createdTemplate = templateService.createTemplate(userId, templateDto);
        return ResponseEntity.ok(createdTemplate);
    }

    @DeleteMapping("/{templateId}")
    public ResponseEntity<Void> deleteTemplate(@PathVariable Long userId, @PathVariable Long templateId) {
        templateService.deleteTemplate(userId, templateId);
        return ResponseEntity.noContent().build();
    }
} 