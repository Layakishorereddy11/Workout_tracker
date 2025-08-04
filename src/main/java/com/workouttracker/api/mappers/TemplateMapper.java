package com.workouttracker.api.mappers;

import com.workouttracker.api.dto.TemplateDto;
import com.workouttracker.api.models.Template;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TemplateExerciseMapper.class})
public interface TemplateMapper {
    @Mapping(source = "userId", target = "userId")
    TemplateDto toDto(Template template);

    @Mapping(target = "id", ignore = true)
    Template toEntity(TemplateDto templateDto);
} 