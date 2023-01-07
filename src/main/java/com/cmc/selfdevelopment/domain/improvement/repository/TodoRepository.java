package com.cmc.selfdevelopment.domain.improvement.repository;

import com.cmc.selfdevelopment.domain.improvement.entity.Improvement;
import com.cmc.selfdevelopment.domain.improvement.entity.Todo;
import com.cmc.selfdevelopment.domain.user.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo,Long> {
//    boolean existsByUserAndImprovementAAndDate(User user, Improvement improvement, Date date);
    Optional<Todo> findByUserAndImprovementAndDate(UserAccount user, Improvement improvement, Date date);
    List<Todo> findByDateAndUser(Date date, UserAccount userAccount);
}
