package com.cmc.selfdevelopment.global.common.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    // diary
    DIARY_CREATED(HttpStatus.CREATED, "계획 생성에 성공하였습니다."),
    IMPROVEMENT_CREATED(HttpStatus.CREATED, "자기 계발 생성에 성공하였습니다."),
    IMPROVEMENT_FOUND(HttpStatus.OK, "자기 계발 찾기에 성공하였습니다."),
    TODO_CHANGE(HttpStatus.OK, "isDone 변경에 성공했습니다."),
    TODO_LIST(HttpStatus.OK, "해당 날짜의 Todo 리스트입니다")
    ;
    private final HttpStatus status;
    private final String message;
}
