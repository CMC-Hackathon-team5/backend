package com.cmc.selfdevelopment.domain.user.repository;

import com.cmc.selfdevelopment.domain.user.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findByEmail(String email);
    UserAccount findByEmailAndPassword(String email, String password);
    List<UserAccount> findAll();
    Optional<UserAccount> findById(Long id);
}
