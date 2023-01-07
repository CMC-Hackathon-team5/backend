package com.cmc.selfdevelopment.domain.diary.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DiaryMapper {
    DiaryMapper INSTANCE = Mappers.getMapper(DiaryMapper.class);
//
//    @Mapping(source = "diary.content")
//    DiaryResponseDto toResponseDto(Diary diary, User user);
}