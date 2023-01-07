package com.cmc.selfdevelopment.domain.user;

import lombok.*;

// TODO : 지워야합니다
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserTempDto {
    String email;
    String password;
    String name;
    int age;
}
