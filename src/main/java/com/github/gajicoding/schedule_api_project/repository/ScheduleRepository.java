package com.github.gajicoding.schedule_api_project.repository;

import com.github.gajicoding.schedule_api_project.data.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
