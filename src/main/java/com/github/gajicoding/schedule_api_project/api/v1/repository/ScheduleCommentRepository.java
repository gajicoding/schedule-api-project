package com.github.gajicoding.schedule_api_project.api.v1.repository;

import com.github.gajicoding.schedule_api_project.api.v1.data.entity.ScheduleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleCommentRepository extends JpaRepository<ScheduleComment, Long> {

}
