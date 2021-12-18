package com.example.server.user.repository;

import com.example.server.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findAllByMajorOrderByWalkDesc(String major);
}
