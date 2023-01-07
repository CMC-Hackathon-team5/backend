package com.cmc.selfdevelopment.domain.improvement.controller;

import com.cmc.selfdevelopment.domain.improvement.dto.ImprovementDto;
import com.cmc.selfdevelopment.domain.improvement.service.ImprovementService;
import com.cmc.selfdevelopment.global.common.api.ApiResponse;
import com.cmc.selfdevelopment.global.common.exception.CustomException;
import com.cmc.selfdevelopment.global.common.api.ErrorCode;
import com.cmc.selfdevelopment.global.common.api.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/improvement-management/improvement")
@Slf4j
public class ImprovementController {

    private final ImprovementService improvementService;
    @Operation(summary = "자기 계발 찾기", description = "title로 자기계발을 찾는 메서드입니다.")
    @GetMapping
    public ResponseEntity<ApiResponse<ImprovementDto>> getImprovement(String title){
        Optional<ImprovementDto> findImprovementDto = improvementService.findByTitle(title);
        if(findImprovementDto == null){
            throw new CustomException(ErrorCode.IMPROVEMENT_NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(ResponseCode.IMPROVEMENT_FOUND, findImprovementDto.get()));
    }

    @Operation(summary = "초기 10개 자기 계발", description = "초기 10개의 자기 계발 값입니다")
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<ImprovementDto>>> getDefaultImprovement(){

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(ResponseCode.IMPROVEMENT_FOUND, improvementService.findDefault()));
    }

    @Operation(summary = "자기 계발 추가", description = "자기계발 추가하는 메서드입니다.")
    @PostMapping
    public ResponseEntity<ApiResponse<ImprovementDto>> createImprovement(@RequestBody ImprovementDto improvementDto){
        log.info(improvementDto.getTitle());
        ImprovementDto success = improvementService.create(improvementDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(ResponseCode.IMPROVEMENT_CREATED, success));
    }


}
