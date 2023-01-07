package com.cmc.selfdevelopment.domain.user;
//TODO : 지워야합니다.
import com.cmc.selfdevelopment.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTempRepository extends JpaRepository<User, Long> {
}
