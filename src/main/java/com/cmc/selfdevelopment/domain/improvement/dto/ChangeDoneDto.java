package com.cmc.selfdevelopment.domain.improvement.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeDoneDto {
    private String title;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date date;
}
