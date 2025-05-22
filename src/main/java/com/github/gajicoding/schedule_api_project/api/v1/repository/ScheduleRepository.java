package com.github.gajicoding.schedule_api_project.api.v1.repository;

import com.github.gajicoding.schedule_api_project.api.v1.data.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    /*
        SELECT s.id, s.title, s.contents, u.name, COUNT(c.id), s.created_at, s.updated_at
        FROM schedules s
        JOIN users u ON s.user_id = u.id
        LEFT JOIN schedule_comments c ON s.id = c.schedule_id
        GROUP BY s.id
        ORDER BY s.updated_at DESC
    */
//    @Query("""
//        SELECT new com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule.SchedulePageResponseDTO(
//            s.id,
//            s.title,
//            s.contents,
//            COUNT(c.id),
//            u.name,
//            s.createdAt,
//            s.updatedAt
//        )
//        FROM Schedule s
//        JOIN s.user u
//        LEFT JOIN ScheduleComment c ON s.id = c.schedule.id
//        GROUP BY s.id, s.title, s.contents, u.name, s.createdAt, s.updatedAt
//        ORDER BY s.updatedAt DESC
//    """)
}
