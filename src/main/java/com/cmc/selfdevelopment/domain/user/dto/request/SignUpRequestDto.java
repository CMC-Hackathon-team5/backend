package com.cmc.selfdevelopment.domain.user.dto.request;

import com.cmc.selfdevelopment.global.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto {
    String email;
    String password;
    String name;
    int age;
}
