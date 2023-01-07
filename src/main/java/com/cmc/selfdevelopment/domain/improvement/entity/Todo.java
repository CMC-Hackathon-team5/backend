package com.cmc.selfdevelopment.domain.improvement.entity;

import com.cmc.selfdevelopment.domain.user.entity.UserAccount;
import com.cmc.selfdevelopment.global.common.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
public class Todo extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount user;

    @ManyToOne
    @JoinColumn(name = "improvement_id")
    private Improvement improvement;

    @ColumnDefault("false")
    private boolean isDone;

//    @Temporal(TemporalType.DATE)
//    private Date date;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date date;

    public void change(){
        isDone = !isDone;
    }

}
