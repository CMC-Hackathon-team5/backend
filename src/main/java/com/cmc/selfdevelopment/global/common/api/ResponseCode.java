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
    DIARY_CREATED(HttpStatus.CREATED, "계획 생성에 성공하였습니다."),
    IMPROVEMENT_CREATED(HttpStatus.CREATED, "자기 계발 생성에 성공하였습니다."),
    IMPROVEMENT_FOUND(HttpStatus.OK, "자기 계발 찾기에 성공하였습니다.")
    ;
    private final HttpStatus status;
    private final String message;
}
