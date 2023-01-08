package com.cmc.selfdevelopment.domain.diary.repository;

import com.cmc.selfdevelopment.domain.diary.entity.Diary;
import com.cmc.selfdevelopment.domain.improvement.entity.Todo;
import com.cmc.selfdevelopment.domain.user.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByUserAccountId(Long userId);

    Optional<Diary> findByDateAndUserAccount(Date date, UserAccount user);

}
