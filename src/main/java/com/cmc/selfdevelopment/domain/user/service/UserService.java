package com.cmc.selfdevelopment.domain.user.service;

import com.cmc.selfdevelopment.domain.user.dto.request.LogInRequstDto;
import com.cmc.selfdevelopment.domain.user.dto.request.SignUpRequestDto;
import com.cmc.selfdevelopment.domain.user.dto.response.AccessTokenDto;
import com.cmc.selfdevelopment.domain.user.dto.response.LogInResponseDto;
import com.cmc.selfdevelopment.domain.user.entity.UserAccount;
import com.cmc.selfdevelopment.domain.user.repository.UserRepository;
import com.cmc.selfdevelopment.global.common.api.ErrorCode;
import com.cmc.selfdevelopment.global.common.exception.CustomException;
import com.cmc.selfdevelopment.global.config.SecurityConfig.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void userSignUp(SignUpRequestDto signUpRequestDto) {
        try {
            UserAccount userAccount = UserAccount.builder()
                    .name(signUpRequestDto.getName())
                    .email(signUpRequestDto.getEmail())
                    .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                    .build();
//            System.out.println(userAccount.toString());
            log.info("user signup userAcoount {}", userAccount);
            UserAccount saved = userRepository.save(userAccount);
            log.info("user signup saved {}", saved);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.PASSWORD_ENCRYPTION_ERROR);
        }
    }

    public UserAccount findUserById(Long id) {
        UserAccount user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        return user;
    }

    public void validationDuplicateEmail(String email) {
        Optional<UserAccount> user = Optional.ofNullable(userRepository.findByEmail(email));
        if (!user.isEmpty()) {
            throw new CustomException(ErrorCode.DUPLICATED_EMAIL);
        }
    }

    public LogInResponseDto userLogIn(LogInRequstDto request) {
        request.setPassword(request.getPassword());
        Optional<UserAccount> user = checkPassword(request);
        String jwt = jwtTokenProvider.createAccessToken(Long.toString(user.get().getId()));
        return new LogInResponseDto(jwt);
    }

    public Optional<UserAccount> checkPassword(LogInRequstDto logInRequstDto) {
        Optional<UserAccount> user = Optional.ofNullable(userRepository.findByEmail(logInRequstDto.getEmail()));
//        log.info("dd {}",passwordEncoder.encode(logInRequstDto.getPassword()));
//        log.info("dd {}",passwordEncoder.encode(user.get().getPassword()));
        if (!passwordEncoder.matches(logInRequstDto.getPassword(),user.get().getPassword())) {
            throw new CustomException(ErrorCode.DUPLICATED_EMAIL);
        }
        return user;
    }
}
