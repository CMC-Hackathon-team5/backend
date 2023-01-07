package com.cmc.selfdevelopment.domain.user;
//TODO : 지워야합니다.

import com.cmc.selfdevelopment.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserTempService {
    private final UserTempRepository userTempRepository;
    public void create(UserTempDto userTempDto){
        User user = User.builder()
                .email(userTempDto.getEmail())
                .password(userTempDto.getPassword())
                .name(userTempDto.getName())
                .age(userTempDto.getAge())
                .build();
        userTempRepository.save(user);
    }
    public Optional<User> findById(Long id){
        return userTempRepository.findById(id);
    }
}
