package com.cmc.selfdevelopment.domain.comment.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentResponseDto {
    private Long id;
    private Long userId;
    private Long diaryId;
    private String content;
}
