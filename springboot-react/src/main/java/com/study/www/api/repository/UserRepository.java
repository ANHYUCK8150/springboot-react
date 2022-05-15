package com.study.www.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.www.api.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
}
