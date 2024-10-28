package com.csiszer.my_shop.repository;

import com.csiszer.my_shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String emil);
}
