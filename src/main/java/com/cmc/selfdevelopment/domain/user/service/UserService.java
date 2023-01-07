package com.cmc.selfdevelopment.domain.user.service;

import com.cmc.selfdevelopment.domain.user.JwtService;
import com.cmc.selfdevelopment.domain.user.SHA256;
import com.cmc.selfdevelopment.domain.user.dto.request.LogInRequstDto;
import com.cmc.selfdevelopment.domain.user.dto.request.ModifyNameRequstDto;
import com.cmc.selfdevelopment.domain.user.dto.request.SignUpRequestDto;
import com.cmc.selfdevelopment.domain.user.dto.response.LogInResponseDto;
import com.cmc.selfdevelopment.domain.user.entity.User;
import com.cmc.selfdevelopment.domain.user.repository.UserRepository;
import com.cmc.selfdevelopment.global.common.api.ErrorCode;
import com.cmc.selfdevelopment.global.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final JwtService jwtService;
    @Transactional
    public void userSignUp(SignUpRequestDto signUpRequestDto) {
        String pwd;
        try {
            pwd = new SHA256().encrypt(signUpRequestDto.getPassword());
            signUpRequestDto.setPassword(pwd);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.PASSWORD_ENCRYPTION_ERROR);
        }
        try {
            User user = User.builder()
                    .name(signUpRequestDto.getName())
                    .email(signUpRequestDto.getEmail())
                    .age(signUpRequestDto.getAge())
                    .password(pwd)
                    .build();
            userRepository.save(user);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.PASSWORD_ENCRYPTION_ERROR);
        }
    }
    public void validationDuplicateEmail(String email) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if(!user.isEmpty()) {
            throw new CustomException(ErrorCode.DUPLICATED_EMAIL);
        }
    }

    public LogInResponseDto userLogIn(LogInRequstDto request) {
        String pwd;
        try {
            pwd = new SHA256().encrypt(request.getPassword());
            request.setPassword(pwd);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.PASSWORD_ENCRYPTION_ERROR);
        }
        Optional<User> user = checkPassword(request);
        try {
            Long id = user.get().getId();
            String jwt = jwtService.createJwt(id);
            return new LogInResponseDto(id, jwt);
        } catch (Exception exception) {
            throw new CustomException(ErrorCode.USERFAILED_LOGIN);
        }
    }

    public Optional<UserAccount> checkPassword(LogInRequstDto logInRequstDto) {
        Optional<UserAccount> user = Optional.ofNullable(userRepository.findByEmail(logInRequstDto.getEmail()));
        if (!passwordEncoder.matches(logInRequstDto.getPassword(),user.get().getPassword())) {
            throw new CustomException(ErrorCode.USER_FAILED_LOGIN);
        }
        return user;
    }

    public void modifyName(ModifyNameRequstDto modifyNameRequstDto, Long id) {
        try {
            Optional<UserAccount> user = userRepository.findById(id);
            user.get().setName(modifyNameRequstDto.getName());
            userRepository.save(user.get());
        } catch (Exception e) {
            throw new CustomException(ErrorCode.USER_FAILED_MODIFIED_NAME);
        }
    }
}
