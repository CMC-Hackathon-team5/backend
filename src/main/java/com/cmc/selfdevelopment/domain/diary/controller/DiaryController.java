package com.cmc.selfdevelopment.domain.diary.controller;

import com.cmc.selfdevelopment.domain.diary.dto.CreateDiaryRequestDto;
import com.cmc.selfdevelopment.domain.diary.dto.DiaryResponseDto;
import com.cmc.selfdevelopment.domain.diary.dto.UpdateDiaryRequestDto;
import com.cmc.selfdevelopment.domain.diary.entity.Diary;
import com.cmc.selfdevelopment.domain.diary.service.DiaryService;
import com.cmc.selfdevelopment.global.common.api.ApiResponse;
import com.cmc.selfdevelopment.global.common.api.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diary")
public class DiaryController {
    private final DiaryService diaryService;

    @Operation(summary = "회고 생성", description = "회고를 생성하는 메소드입니다.")
    @PostMapping
    public ResponseEntity<ApiResponse> createDiary(@RequestBody CreateDiaryRequestDto createDiaryRequestDto) {
        String content = createDiaryRequestDto.getContent();

        // userId 받아오는거 붙이기
        Long userId = 1L;

        diaryService.createDiary(userId, content);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(ResponseCode.DIARY_CREATED));
    }

    @Operation(summary = "회고 전체 조회", description = "유저의 회고 목록을 조회하는 메소드입니다.")
    @GetMapping
    public ResponseEntity<ApiResponse<List<DiaryResponseDto>>> getAllDiary() {
        // userId 받아오는거 붙이기
        Long userId = 1L;

        List<DiaryResponseDto> diaries = diaryService.getAllDiaries(userId);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ResponseCode.GET_ALL_DIARIES, diaries));
    }

    @Operation(summary = "회고 수정", description = "회고 내용을 수정하는 메소드입니다.")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateDiary(@RequestBody UpdateDiaryRequestDto updateDiaryRequestDto,
                                                                     @PathVariable("id") Long contentId) {
        String content = updateDiaryRequestDto.getContent();
        diaryService.updateDiary(contentId, content);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ResponseCode.DIARY_UPDATED));
    }

    @Operation(summary = "회고 상세 조회", description = "회고 하나를 조회하는 메소드입니다.")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DiaryResponseDto>> getDiary(@PathVariable("id") Long contentId) {
        log.info("contentId {}", contentId);
        DiaryResponseDto diary = diaryService.getDiary(contentId);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ResponseCode.GET_DIARY, diary));
    }

    @Operation(summary = "회고 삭제", description = "회고 삭제를 하는 메소드입니다.")
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteDiary(@PathVariable("id") Long contentId) {
        diaryService.deleteDiary(contentId);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ResponseCode.DIARY_DELETED));
    }
}
