package com.cmc.selfdevelopment.domain.diary;

import com.cmc.selfdevelopment.global.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Table(name = "Diary")
@Entity
public class Diary extends BaseEntity {
    @Setter @Column(nullable = false, length = 500) private String title;
    @Setter @Column(length = 500) private String content;
    @Setter @Column(length = 20) private Date date;
    @Setter @Column(nullable = false) Long user_id;
}
