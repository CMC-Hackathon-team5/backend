package com.cmc.selfdevelopment.domain.user.service;

import com.cmc.selfdevelopment.domain.user.JwtService;
import com.cmc.selfdevelopment.domain.user.dto.request.LogInRequstDto;
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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtService jwtService;

    @Transactional
    public void userSignUp(SignUpRequestDto signUpRequestDto) {
        try {
            User user = User.builder()
                    .name(signUpRequestDto.getName())
                    .email(signUpRequestDto.getEmail())
                    .age(signUpRequestDto.getAge())
                    .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                    .build();
            userRepository.save(user);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.PASSWORD_ENCRYPTION_ERROR);
        }
    }

    public void validationDuplicateEmail(String email) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if (!user.isEmpty()) {
            throw new CustomException(ErrorCode.DUPLICATED_EMAIL);
        }
    }

    public LogInResponseDto userLogIn(LogInRequstDto request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        Optional<User> user = checkPassword(request);
        try {
            Long id = user.get().getId();
            String jwt = jwtService.createJwt(id);
            return new LogInResponseDto(id, jwt);
        } catch (Exception exception) {
            throw new CustomException(ErrorCode.USERFAILED_LOGIN);
        }
    }

    public Optional<User> checkPassword(LogInRequstDto logInRequstDto) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmailAndPassword(logInRequstDto.getEmail(), logInRequstDto.getPassword()));
        if (user.isEmpty()) {
            throw new CustomException(ErrorCode.DUPLICATED_EMAIL);
        }
        return user;
    }
}
