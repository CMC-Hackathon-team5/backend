package com.cmc.selfdevelopment.domain.improvement.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
    private String title;
    private boolean isDone;
    private Date date;
}
