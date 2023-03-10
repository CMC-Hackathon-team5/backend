package com.cmc.selfdevelopment.domain.test;

import com.cmc.selfdevelopment.global.common.api.ApiResponse;
import com.cmc.selfdevelopment.global.common.exception.CustomException;
import com.cmc.selfdevelopment.global.common.api.ErrorCode;
import com.cmc.selfdevelopment.global.common.api.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class TestController {
    @PostMapping("/success")
    public ResponseEntity<ApiResponse<String>> testSuccess() {
        String data = "test api success";
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("test controller {}", principal.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(ResponseCode.DIARY_CREATED, data));
    }

    @PostMapping("/fail")
    public ResponseEntity<ApiResponse<String>> testFail() {
        String data = "test api fail";
        throw new CustomException(ErrorCode.DIARY_NOT_FOUND);
    }
}
