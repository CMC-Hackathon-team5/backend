package com.cmc.selfdevelopment.domain.comment.entity;

import com.cmc.selfdevelopment.domain.diary.entity.Diary;
import com.cmc.selfdevelopment.domain.user.entity.User;
import com.cmc.selfdevelopment.global.common.entity.BaseEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Table(name = "Comment")
@SuperBuilder
@RequiredArgsConstructor
@Entity
public class Comment extends BaseEntity {
    @ManyToOne @Setter @JoinColumn(name = "user_id", nullable = false) private User user;
    @ManyToOne @Setter @JoinColumn(name = "diary_id", nullable = false) private Diary diary;
    @Setter private String content;
}
