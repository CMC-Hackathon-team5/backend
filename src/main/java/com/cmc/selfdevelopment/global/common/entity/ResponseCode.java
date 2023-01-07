package com.cmc.selfdevelopment.global.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    // diary
    DIARY_CREATED(HttpStatus.CREATED, "계획 생성에 성공하였습니다.");

    private final HttpStatus status;
    private final String message;
}
