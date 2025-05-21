package com.github.gajicoding.schedule_api_project.repository;

import com.github.gajicoding.schedule_api_project.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
