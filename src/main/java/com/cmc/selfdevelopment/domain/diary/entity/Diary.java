package com.cmc.selfdevelopment.domain.diary.entity;

import com.cmc.selfdevelopment.domain.user.entity.UserAccount;
import com.cmc.selfdevelopment.global.common.entity.BaseEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Table(name = "Diary")
@SuperBuilder
@RequiredArgsConstructor
@Entity
public class Diary extends BaseEntity {
    @Setter @Column(length = 500) private String content;

    @ManyToOne @Setter @JoinColumn(name = "user_id", nullable = false)
    private UserAccount userAccount;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date date;
}
