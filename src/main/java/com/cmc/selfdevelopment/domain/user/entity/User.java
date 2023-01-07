package com.cmc.selfdevelopment.domain.user.entity;

import com.cmc.selfdevelopment.global.common.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
public class User extends BaseEntity {
    String email;
    String password;
    String name;
    int age;
}
