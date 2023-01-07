package com.cmc.selfdevelopment.domain.improvement.dto.mapper;

import com.cmc.selfdevelopment.domain.improvement.dto.ImprovementDto;
import com.cmc.selfdevelopment.domain.improvement.entity.Improvement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ImprovementMapper {
    ImprovementMapper INSTANCE = Mappers.getMapper(ImprovementMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "modifyDate", ignore = true)
    @Mapping(source = "title", target = "title")
    Improvement toEntity(ImprovementDto improvementDto);

    @Mapping(source = "title", target = "title")
    ImprovementDto toDto(Improvement improvement);
}