package com.cmc.selfdevelopment.domain.diary.controller;

import com.cmc.selfdevelopment.domain.diary.dto.CreateDiaryRequestDto;
import com.cmc.selfdevelopment.domain.diary.dto.DiaryResponseDto;
import com.cmc.selfdevelopment.domain.diary.dto.UpdateDiaryRequestDto;
import com.cmc.selfdevelopment.domain.diary.entity.Diary;
import com.cmc.selfdevelopment.domain.diary.service.DiaryService;
import com.cmc.selfdevelopment.domain.user.entity.UserAccount;
import com.cmc.selfdevelopment.domain.user.service.UserService;
import com.cmc.selfdevelopment.global.common.api.ApiResponse;
import com.cmc.selfdevelopment.global.common.api.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diary")
public class DiaryController {
    private final DiaryService diaryService;

    private final UserService userService;

    @Operation(summary = "회고 생성", description = "회고를 생성하는 메소드입니다.")
    @PostMapping
    public ResponseEntity<ApiResponse> createDiary(
            @RequestBody CreateDiaryRequestDto createDiaryRequestDto,
            @AuthenticationPrincipal User user) {
        String content = createDiaryRequestDto.getContent();

        Long userId = Long.parseLong(user.getUsername());
        Date date = createDiaryRequestDto.getDate();
        diaryService.createDiary(userId, content, date);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(ResponseCode.DIARY_CREATED));
    }

    @Operation(summary = "회고 전체 조회", description = "유저의 회고 목록을 조회하는 메소드입니다.")
    @GetMapping
    public ResponseEntity<ApiResponse<List<DiaryResponseDto>>> getAllDiary(@AuthenticationPrincipal User user) {
        Long userId = Long.parseLong(user.getUsername());

        List<DiaryResponseDto> diaries = diaryService.getAllDiaries(userId);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ResponseCode.GET_ALL_DIARIES, diaries));
    }

    @Operation(summary = "회고 수정", description = "회고 내용을 수정하는 메소드입니다.")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateDiary(
            @RequestBody UpdateDiaryRequestDto updateDiaryRequestDto,
            @PathVariable("id") Long contentId
    ) {
        String content = updateDiaryRequestDto.getContent();
        diaryService.updateDiary(contentId, content);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ResponseCode.DIARY_UPDATED));
    }

    @Operation(summary = "회고 상세 조회", description = "회고 하나를 조회하는 메소드입니다.")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DiaryResponseDto>> getDiary(@PathVariable("id") Long contentId) {

        DiaryResponseDto diary = diaryService.getDiary(contentId);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ResponseCode.GET_DIARY, diary));
    }
    // 위의 코드를 살리기 위해 새로 API만들었습니다. 애슐리님 확인 바랍니다!
    @Operation(summary = "회고 날짜별 상세 조회", description = "특정 날짜의 회고 하나를 조회하는 메소드입니다.")
    @GetMapping("/day")
    public ResponseEntity<ApiResponse<DiaryResponseDto>> getDayDiary(@RequestParam("date") Date date,
                                                                     @AuthenticationPrincipal User user) {
        UserAccount findUser = userService.findUserById(Long.parseLong(user.getUsername()));
        DiaryResponseDto diary = diaryService.getDayDiary(date, findUser);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ResponseCode.GET_DIARY, diary));
    }
    @Operation(summary = "회고 삭제", description = "회고 삭제를 하는 메소드입니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDiary(@PathVariable("id") Long contentId) {
        diaryService.deleteDiary(contentId);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ResponseCode.DIARY_DELETED));
    }
}
