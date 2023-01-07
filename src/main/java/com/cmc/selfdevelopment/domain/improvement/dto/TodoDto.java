package com.cmc.selfdevelopment.domain.improvement.dto;

import lombok.*;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
    private String title;
    private boolean isDone;
}
