package com.cmc.selfdevelopment.domain.user.repository;

import com.cmc.selfdevelopment.domain.test.Test;
import com.cmc.selfdevelopment.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByEmailAndPassword(String email,String password);
    List<User> findAll();
}
