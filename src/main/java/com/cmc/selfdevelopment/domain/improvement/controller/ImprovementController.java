package com.cmc.selfdevelopment.domain.improvement.controller;

import com.cmc.selfdevelopment.domain.improvement.dto.*;
import com.cmc.selfdevelopment.domain.improvement.service.ImprovementService;
import com.cmc.selfdevelopment.domain.improvement.service.TodoService;
import com.cmc.selfdevelopment.domain.user.entity.UserAccount;
import com.cmc.selfdevelopment.domain.user.service.UserService;
import com.cmc.selfdevelopment.global.common.api.ApiResponse;
import com.cmc.selfdevelopment.global.common.exception.CustomException;
import com.cmc.selfdevelopment.global.common.api.ErrorCode;
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

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/improvement-management")
@Slf4j
public class ImprovementController {

    private final ImprovementService improvementService;
    private final TodoService todoService;
    private final UserService userService;

    @Operation(summary = "자기 계발 찾기", description = "title로 자기계발을 찾는 메서드입니다.")
    @GetMapping("/improvement")
    public ResponseEntity<ApiResponse<ImprovementDto>> getImprovement(String title){
        Optional<ImprovementDto> findImprovementDto = improvementService.findByTitle(title);
        if(findImprovementDto == null){
            throw new CustomException(ErrorCode.IMPROVEMENT_NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(ResponseCode.IMPROVEMENT_FOUND, findImprovementDto.get()));
    }

    @Operation(summary = "초기 10개 자기 계발", description = "초기 10개의 자기 계발 값입니다")
    @GetMapping("/improvement/list")
    public ResponseEntity<ApiResponse<List<ImprovementDto>>> getDefaultImprovement(){

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(ResponseCode.IMPROVEMENT_FOUND, improvementService.findDefault()));
    }

    @Operation(summary = "자기 계발 데이터 추가", description = "자기계발 추가하는 메서드입니다.")
    @PostMapping("/improvement")
    public ResponseEntity<ApiResponse<ImprovementDto>> createImprovement(@RequestBody ImprovementDto improvementDto){
        log.info(improvementDto.getTitle());
        ImprovementDto success = improvementService.create(improvementDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(ResponseCode.IMPROVEMENT_CREATED, success));
    }

    @Operation(summary = "Todo 리스트 추가", description = "자기계발을 Todo에 추가하는 메서드입니다")
    @PostMapping("/todo")
    public ResponseEntity<ApiResponse<TodoDto>> createTodo(@RequestBody CreateTodoDto createTodoDto,
                                                           @AuthenticationPrincipal User user){
        UserAccount findUser = userService.findUserById(Long.parseLong(user.getUsername()));
        TodoDto todoDto = todoService.create(createTodoDto, findUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(ResponseCode.IMPROVEMENT_CREATED, todoDto));
    }

    @Operation(summary = "Todo 체크 변경", description = "Todo 체크 변경 하는 메서드입니다")
    @PostMapping("/todo/check")
    public ResponseEntity<ApiResponse<TodoDto>> changeTodo(@RequestBody ChangeDoneDto changeDoneDto,
                                                           @AuthenticationPrincipal User user){
        UserAccount findUser = userService.findUserById(Long.parseLong(user.getUsername()));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(ResponseCode.TODO_CHANGE, todoService.changeCheck(findUser, changeDoneDto)));
    }

    @Operation(summary = "날짜 별 Todo", description = "날짜 별 Todo리스트를 보여주는 메서드입니다")
    @GetMapping("/todo")
    public ResponseEntity<ApiResponse<List<TodoDto>>> showTodo(@RequestParam("date") Date date,
                                                               @AuthenticationPrincipal User user){
        UserAccount findUser = userService.findUserById(Long.parseLong(user.getUsername()));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(ResponseCode.TODO_LIST, todoService.findAllTodo(date, findUser)));
    }

    @Operation(summary = "특정 월에 해당하는 퍼센트", description = "특정 월에 해당하는 모든 날짜의 완성도 퍼센티지를 리턴")
    @GetMapping("/todo/month")
    public ResponseEntity<ApiResponse<List<TodoPercentDto>>> showTodoPercent(@RequestParam("date") Date date,
                                                                             @AuthenticationPrincipal User user){
        UserAccount findUser = userService.findUserById(Long.parseLong(user.getUsername()));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(ResponseCode.TODO_MONTH_PERCENT, todoService.findMonthPercent(date, findUser)));
    }
}
