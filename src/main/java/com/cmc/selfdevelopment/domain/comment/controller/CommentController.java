package com.cmc.selfdevelopment.domain.comment.controller;

import com.cmc.selfdevelopment.domain.comment.dto.CreateCommentRequestDto;
import com.cmc.selfdevelopment.domain.comment.service.CommentService;
import com.cmc.selfdevelopment.global.common.api.ApiResponse;
import com.cmc.selfdevelopment.global.common.api.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;

    @Operation(summary = "댓글 생성", description = "댓글을 생성하는 메소드입니다.")
    @PostMapping
    public ResponseEntity<ApiResponse> createComment(@RequestBody CreateCommentRequestDto createCommentRequestDto) {
        // TODO: userId 받아오는 메소드 연결
        Long userId = 1L;

        Long diaryId = createCommentRequestDto.getDiaryId();
        String content = createCommentRequestDto.getContent();

        commentService.createComment(userId, diaryId, content);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(ResponseCode.COMMENT_CREATED));
    }
}
