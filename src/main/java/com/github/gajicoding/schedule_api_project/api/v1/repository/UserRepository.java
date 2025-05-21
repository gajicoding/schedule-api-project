package com.github.gajicoding.schedule_api_project.api.v1.repository;

import com.github.gajicoding.schedule_api_project.api.v1.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
}
