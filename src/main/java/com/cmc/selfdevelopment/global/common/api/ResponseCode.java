package com.cmc.selfdevelopment.global.common.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    // user
    USER_SIGNUP(HttpStatus.CREATED,"회원 가입에 성공하였습니다."),
    USER_LOGIN(HttpStatus.OK,"로그인에 성공하였습니다."),
    USER_PROFILE(HttpStatus.OK,"로그인에 성공하였습니다."),
    // diary
    DIARY_CREATED(HttpStatus.CREATED, "회고 생성에 성공하였습니다."),
    IMPROVEMENT_CREATED(HttpStatus.CREATED, "자기 계발 생성에 성공하였습니다."),
    GET_ALL_DIARIES(HttpStatus.OK, "회고 전체 조회에 성공하였습니다."),
    IMPROVEMENT_FOUND(HttpStatus.OK, "자기 계발 찾기에 성공하였습니다."),
    DIARY_UPDATED(HttpStatus.OK, "회고 수정에 성공하였습니다."),
    GET_DIARY(HttpStatus.OK, "회고 조회에 성공하였습니다."),
    DIARY_DELETED(HttpStatus.OK, "회고 삭제에 성공하였습니다." ),
    TODO_CHANGE(HttpStatus.OK, "isDone 변경에 성공했습니다."),
    TODO_LIST(HttpStatus.OK, "해당 날짜의 Todo 리스트입니다"),
    TODO_MONTH_PERCENT(HttpStatus.OK, "특정 달의 TODO 성취도 입니다")
    ;
    private final HttpStatus status;
    private final String message;
}
