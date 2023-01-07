package com.cmc.selfdevelopment.domain.diary.dto.mapper;

import com.cmc.selfdevelopment.domain.diary.dto.DiaryResponseDto;
import com.cmc.selfdevelopment.domain.diary.entity.Diary;
import com.cmc.selfdevelopment.domain.improvement.dto.ImprovementDto;
import com.cmc.selfdevelopment.domain.improvement.entity.Improvement;
import com.cmc.selfdevelopment.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DiaryMapper {
    DiaryMapper INSTANCE = Mappers.getMapper(DiaryMapper.class);
//
//    @Mapping(source = "diary.content")
//    DiaryResponseDto toResponseDto(Diary diary, User user);
}