package com.cmc.selfdevelopment.domain.diary.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class DiaryResponseDto {
    private Long id;
    private Long user_id;
    private String content;
    private Date date;
}
