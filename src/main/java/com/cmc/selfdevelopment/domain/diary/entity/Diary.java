package com.cmc.selfdevelopment.domain.diary.entity;

import com.cmc.selfdevelopment.domain.user.entity.UserAccount;
import com.cmc.selfdevelopment.global.common.entity.BaseEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Table(name = "Diary")
@SuperBuilder
@RequiredArgsConstructor
@Entity
public class Diary extends BaseEntity {
    @Setter @Column(length = 500) private String content;

    @ManyToOne @Setter @JoinColumn(name = "user_id", nullable = false)
    private UserAccount userAccount;


}
