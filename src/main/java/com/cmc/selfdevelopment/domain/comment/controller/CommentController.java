package com.cmc.selfdevelopment.domain.comment.controller;

import com.cmc.selfdevelopment.domain.comment.dto.CommentResponseDto;
import com.cmc.selfdevelopment.domain.comment.dto.CreateCommentRequestDto;
import com.cmc.selfdevelopment.domain.comment.dto.GetCommentsRequestDto;
import com.cmc.selfdevelopment.domain.comment.dto.UpdateCommentRequestDto;
import com.cmc.selfdevelopment.domain.comment.service.CommentService;
import com.cmc.selfdevelopment.global.common.api.ApiResponse;
import com.cmc.selfdevelopment.global.common.api.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;

    @Operation(summary = "댓글 생성", description = "댓글을 생성하는 메소드입니다.")
    @PostMapping
    public ResponseEntity<ApiResponse> createComment(@RequestBody CreateCommentRequestDto createCommentRequestDto, @AuthenticationPrincipal User user) {
        Long userId = Long.parseLong(user.getUsername());

        Long diaryId = createCommentRequestDto.getDiaryId();
        String content = createCommentRequestDto.getContent();

        commentService.createComment(userId, diaryId, content);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(ResponseCode.COMMENT_CREATED));
    }

    @Operation(summary = "댓글 전체 조회", description = "회고에 해당하는 댓글 전체 조회하는 메소드입니다.")
    @GetMapping
    public ResponseEntity<ApiResponse<List<CommentResponseDto>>> getComments(@RequestBody GetCommentsRequestDto getCommentsRequestDto) {
        Long diaryId = getCommentsRequestDto.getDiaryId();

        List<CommentResponseDto> comments = commentService.getComments(diaryId);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ResponseCode.GET_COMMENTS_SUCCESS, comments));
    }

    @Operation(summary = "댓글 수정", description = "댓글을 수정하는 메소드입니다.")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateComment(@PathVariable("id") Long commentId, @RequestBody UpdateCommentRequestDto updateCommentRequestDto) {
        String content = updateCommentRequestDto.getContent();
        commentService.updateComment(commentId, content);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ResponseCode.COMMENT_UPDATED));
    }

    @Operation(summary = "댓글 삭제", description = "댓글을 삭제하는 메소드입니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable("id") Long commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(ResponseCode.COMMENT_DELETED));
    }
}
