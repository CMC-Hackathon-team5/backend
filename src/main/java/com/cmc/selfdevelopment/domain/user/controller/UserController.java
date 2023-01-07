package com.cmc.selfdevelopment.domain.user.controller;

import com.cmc.selfdevelopment.domain.user.dto.request.LogInRequstDto;
import com.cmc.selfdevelopment.domain.user.dto.request.ModifyNameRequstDto;
import com.cmc.selfdevelopment.domain.user.dto.request.SignUpRequestDto;
import com.cmc.selfdevelopment.domain.user.dto.response.LogInResponseDto;
import com.cmc.selfdevelopment.domain.user.service.UserService;
import com.cmc.selfdevelopment.global.common.api.ApiResponse;
import com.cmc.selfdevelopment.global.common.api.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@RequestBody SignUpRequestDto request) {
        userService.validationDuplicateEmail(request.getEmail());
        userService.userSignUp(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(ResponseCode.USER_SIGNUP));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LogInResponseDto>> logIn(@RequestBody LogInRequstDto request) {
        LogInResponseDto logInResponseDto = userService.userLogIn(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(ResponseCode.USER_SIGNUP,logInResponseDto));
    }

    @PatchMapping ("/profile/modifyName")
    public ResponseEntity<ApiResponse> modifyName(@RequestBody ModifyNameRequstDto modifyNameRequstDto) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.modifyName(modifyNameRequstDto,Long.parseLong(principal.getUsername()));
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(ResponseCode.USER_MODIFIED_NAME,modifyNameRequstDto));
    }
}
