package com.cmc.selfdevelopment.global.common.exception;

import com.cmc.selfdevelopment.global.common.api.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;
}