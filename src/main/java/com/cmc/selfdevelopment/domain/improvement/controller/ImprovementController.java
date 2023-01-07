package com.cmc.selfdevelopment.domain.improvement.controller;

import com.cmc.selfdevelopment.domain.improvement.dto.ChangeDoneDto;
import com.cmc.selfdevelopment.domain.improvement.dto.CreateTodoDto;
import com.cmc.selfdevelopment.domain.improvement.dto.ImprovementDto;
import com.cmc.selfdevelopment.domain.improvement.dto.TodoDto;
import com.cmc.selfdevelopment.domain.improvement.service.ImprovementService;
import com.cmc.selfdevelopment.domain.improvement.service.TodoService;
import com.cmc.selfdevelopment.domain.user.UserTempService;
import com.cmc.selfdevelopment.domain.user.entity.User;
import com.cmc.selfdevelopment.global.common.api.ApiResponse;
import com.cmc.selfdevelopment.global.common.exception.CustomException;
import com.cmc.selfdevelopment.global.common.api.ErrorCode;
import com.cmc.selfdevelopment.global.common.api.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/improvement-management")
@Slf4j
public class ImprovementController {

    private final ImprovementService improvementService;
    private final TodoService todoService;
    private final UserTempService userTempService;
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
    public ResponseEntity<ApiResponse<TodoDto>> createTodo(@RequestBody CreateTodoDto createTodoDto){
        // TODO : 회원가입이후 수정해야합니다.
        User user = userTempService.findById(1L).get();
        TodoDto todoDto = todoService.create(createTodoDto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(ResponseCode.IMPROVEMENT_CREATED, todoDto));
    }

    @Operation(summary = "Todo 체크 변경", description = "Todo 체크 변경 하는 메서드입니다")
    @PostMapping("/todo/check")
    public ResponseEntity<ApiResponse<TodoDto>> changeTodo(@RequestBody ChangeDoneDto changeDoneDto){
        // TODO : 회원가입이후 수정해야합니다.
        User user = userTempService.findById(1L).get();
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(ResponseCode.TODO_CHANGE, todoService.changeCheck(user, changeDoneDto)));
    }

    @Operation(summary = "날짜 별 Todo", description = "날짜 별 Todo리스트를 보여주는 메서드입니다")
    @GetMapping("/todo")
    public ResponseEntity<ApiResponse<List<TodoDto>>> showTodo(@RequestParam("date") Date date){

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(ResponseCode.TODO_LIST, todoService.findAllTodo(date)));
    }

}
