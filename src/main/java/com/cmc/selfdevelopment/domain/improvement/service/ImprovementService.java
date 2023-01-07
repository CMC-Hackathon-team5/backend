package com.cmc.selfdevelopment.domain.improvement.service;

import com.cmc.selfdevelopment.domain.improvement.dto.ImprovementDto;
import com.cmc.selfdevelopment.domain.improvement.dto.mapper.ImprovementMapper;
import com.cmc.selfdevelopment.domain.improvement.entity.Improvement;
import com.cmc.selfdevelopment.domain.improvement.repository.ImprovementRepository;
import com.cmc.selfdevelopment.global.common.exception.CustomException;
import com.cmc.selfdevelopment.global.common.api.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImprovementService {

    private final ImprovementRepository improvementRepository;

    public Optional<ImprovementDto> findByTitle(String title){
        if(improvementRepository.findByTitle(title).isEmpty()){
            return null;
        }

        return Optional.ofNullable(ImprovementMapper.INSTANCE.toDto(improvementRepository.findByTitle(title).get()));
    }
    public ImprovementDto create(ImprovementDto improvementDto) {
        if(findByTitle(improvementDto.getTitle()) != null){
            throw new CustomException(ErrorCode.IMPROVEMENT_NOT_FOUND);
        }
        improvementRepository.save(ImprovementMapper.INSTANCE.toEntity(improvementDto));
        return improvementDto;
    }

    public List<ImprovementDto> findDefault() {
        return improvementRepository.findTop10ByOrderByIdAsc().stream()
                .map(improvement -> ImprovementMapper.INSTANCE.toDto(improvement))
                .collect(Collectors.toList());
    }

}
