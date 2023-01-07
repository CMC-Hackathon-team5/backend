package com.cmc.selfdevelopment.domain.improvement.entity;

import com.cmc.selfdevelopment.global.common.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
public class Improvement extends BaseEntity {
    @Column(nullable = false)
    private String title;
}
