package com.cmc.selfdevelopment.domain.comment.dto;

import lombok.Getter;

@Getter
public class CreateCommentRequestDto {
    private String content;
    private Long diaryId;
}
