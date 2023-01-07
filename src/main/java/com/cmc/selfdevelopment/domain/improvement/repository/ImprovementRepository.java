package com.cmc.selfdevelopment.domain.improvement.repository;

import com.cmc.selfdevelopment.domain.improvement.entity.Improvement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImprovementRepository extends JpaRepository<Improvement,Long> {
    Optional<Improvement> findByTitle(String title);

    List<Improvement> findTop10ByOrderByIdAsc();
}
