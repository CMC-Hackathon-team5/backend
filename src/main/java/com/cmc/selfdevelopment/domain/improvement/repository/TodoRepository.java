package com.cmc.selfdevelopment.domain.improvement.repository;

import com.cmc.selfdevelopment.domain.improvement.entity.Improvement;
import com.cmc.selfdevelopment.domain.improvement.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Long> {
}
