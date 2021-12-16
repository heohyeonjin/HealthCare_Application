package com.example.server.user.repository;

import com.example.server.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserEmail(String email);
}
