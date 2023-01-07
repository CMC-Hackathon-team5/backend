package com.cmc.selfdevelopment.domain.user.dto.request;

import com.cmc.selfdevelopment.global.common.entity.BaseEntity;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignUpRequestDto {
    String email;
    String password;
    String name;
}
