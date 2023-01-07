package com.cmc.selfdevelopment.domain.improvement.dto.mapper;

import com.cmc.selfdevelopment.domain.improvement.dto.ImprovementDto;
import com.cmc.selfdevelopment.domain.improvement.entity.Improvement;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-08T05:13:12+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.12 (Microsoft)"
)
public class ImprovementMapperImpl implements ImprovementMapper {

    @Override
    public Improvement toEntity(ImprovementDto improvementDto) {
        if ( improvementDto == null ) {
            return null;
        }

        Improvement.ImprovementBuilder<?, ?> improvement = Improvement.builder();

        improvement.title( improvementDto.getTitle() );

        return improvement.build();
    }

    @Override
    public ImprovementDto toDto(Improvement improvement) {
        if ( improvement == null ) {
            return null;
        }

        ImprovementDto.ImprovementDtoBuilder improvementDto = ImprovementDto.builder();

        improvementDto.title( improvement.getTitle() );

        return improvementDto.build();
    }
}
