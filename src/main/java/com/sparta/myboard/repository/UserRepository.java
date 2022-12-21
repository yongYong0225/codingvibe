package com.sparta.myboard.repository;

import com.sparta.myboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginId(String name);
    Optional<User> findByNickname(String name);
    Boolean existsByIdAndLoginId(Long id, String loginId);
}
