package com.cmc.selfdevelopment.domain.diary.repository;

import com.cmc.selfdevelopment.domain.diary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByUserAccountId(Long userId);
}
