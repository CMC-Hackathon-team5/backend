package com.cmc.selfdevelopment.domain.diary.dto;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Getter
public class CreateDiaryRequestDto {
    private String content;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date date;
}
