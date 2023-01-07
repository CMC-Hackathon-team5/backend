package com.cmc.selfdevelopment.domain.improvement.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoPercentDto {
    private int percent;
    private Date date;
}
