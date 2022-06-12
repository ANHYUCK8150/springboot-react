package com.study.www.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.www.api.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	Boolean existsByEmail(String email);

	List<User> getByName(String nickname);

	Boolean existsByName(String nickname);

	Optional<User> findByProviderId(String id);
}
