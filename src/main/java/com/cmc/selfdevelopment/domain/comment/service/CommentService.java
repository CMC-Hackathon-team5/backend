package com.cmc.selfdevelopment.domain.comment.service;

import com.cmc.selfdevelopment.domain.comment.dto.CommentResponseDto;
import com.cmc.selfdevelopment.domain.comment.entity.Comment;
import com.cmc.selfdevelopment.domain.comment.repository.CommentRepository;
import com.cmc.selfdevelopment.domain.diary.entity.Diary;
import com.cmc.selfdevelopment.domain.diary.repository.DiaryRepository;
import com.cmc.selfdevelopment.domain.user.entity.User;
import com.cmc.selfdevelopment.domain.user.repository.UserRepository;
import com.cmc.selfdevelopment.global.common.api.ErrorCode;
import com.cmc.selfdevelopment.global.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final DiaryRepository diaryRepository;

    @Transactional
    public void createComment(Long userId, Long diaryId, String content) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new CustomException(ErrorCode.DIARY_NOT_FOUND));

        Comment comment = Comment.builder()
                .user(user)
                .diary(diary)
                .content(content)
                .build();

        commentRepository.save(comment);
        return;
    }

    public List<CommentResponseDto> getComments(Long diaryId) {
        List<Comment> comments = commentRepository.findByDiaryId(diaryId);
        List<CommentResponseDto> commentResponseDtos = comments.stream().map((comment ->
                CommentResponseDto.builder()
                        .id(comment.getId())
                        .userId(comment.getUser().getId())
                        .diaryId(comment.getDiary().getId())
                        .content(comment.getContent())
                        .build())
                ).collect(Collectors.toList());
        return commentResponseDtos;
    }

    @Transactional
    public void updateComment(Long commentId, String content) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND))
        comment.setContent(content);
        commentRepository.save(comment);
        return;
    }
}
