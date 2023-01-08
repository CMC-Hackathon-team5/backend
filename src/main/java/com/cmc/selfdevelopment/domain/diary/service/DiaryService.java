package com.cmc.selfdevelopment.domain.diary.service;

import com.cmc.selfdevelopment.domain.diary.dto.DiaryResponseDto;
import com.cmc.selfdevelopment.domain.diary.entity.Diary;
import com.cmc.selfdevelopment.domain.diary.repository.DiaryRepository;
import com.cmc.selfdevelopment.domain.user.entity.UserAccount;
import com.cmc.selfdevelopment.domain.user.repository.UserRepository;
import com.cmc.selfdevelopment.global.common.api.ErrorCode;
import com.cmc.selfdevelopment.global.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createDiary(Long userId, String content, Date date) {
        UserAccount userAccount = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Diary diary = Diary.builder()
                .userAccount(userAccount)
                .content(content)
                .date(date)
                .build();

        diaryRepository.save(diary);

        return;
    }

    @Transactional
    public List<DiaryResponseDto> getAllDiaries(Long userId) {
        List<Diary> diaries = diaryRepository.findByUserAccountId(userId);
//        List<DiaryResponseDto> diariesResponseDto = diaries.stream().map(DiaryMapper.INSTANCE::toResponseDto).collect(Collectors.toList());

        List<DiaryResponseDto> diariesResponseDto = diaries.stream()
                .map((diary -> DiaryResponseDto.builder()
                        .id(diary.getId())
                        .user_id(diary.getUserAccount().getId())
                        .content(diary.getContent())
                        .build()))
                .collect(Collectors.toList());
        return diariesResponseDto;
    }

    @Transactional
    public void updateDiary(Long contentId, String content) {
        Diary diary = diaryRepository.findById(contentId)
                .orElseThrow(() -> new CustomException(ErrorCode.DIARY_NOT_FOUND));
        diary.setContent(content);
        log.info("updated content {}", diary);
        diaryRepository.save(diary);
        return;
    }

    public DiaryResponseDto getDiary(Long contentId) {
        Diary diary = diaryRepository.findById(contentId)
                .orElseThrow(() -> new CustomException(ErrorCode.DIARY_NOT_FOUND));
//        DiaryResponseDto diaryResponseDto = DiaryMapper.INSTANCE.toResponseDto(diary);
        DiaryResponseDto diaryResponseDto = DiaryResponseDto.builder()
                .id(diary.getId())
                .user_id(diary.getUserAccount().getId())
                .content(diary.getContent())
                .build();
        return diaryResponseDto;
    }
    public DiaryResponseDto getDayDiary(Date date, UserAccount userAccount) {
        Diary diary = diaryRepository.findByDateAndUserAccount(date, userAccount)
                .orElseThrow(() -> new CustomException(ErrorCode.DIARY_NOT_FOUND));
//        DiaryResponseDto diaryResponseDto = DiaryMapper.INSTANCE.toResponseDto(diary);
        DiaryResponseDto diaryResponseDto = DiaryResponseDto.builder()
                .id(diary.getId())
                .user_id(diary.getUserAccount().getId())
                .content(diary.getContent())
                .date(date)
                .build();
        return diaryResponseDto;
    }
    public void deleteDiary(Long contentId) {
        Diary diary = diaryRepository.findById(contentId)
                .orElseThrow(() -> new CustomException(ErrorCode.DIARY_NOT_FOUND));
        diaryRepository.delete(diary);
    }
}
