package com.github.gajicoding.schedule_api_project.api.v1.repository;

import com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule.SchedulePageResponseDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {


    @Query("""
        SELECT new com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule.SchedulePageResponseDTO(
            s.id,
            s.title,
            s.contents,
            COUNT(c.id),
            u.name,
            s.createdAt,
            s.updatedAt
        )
        FROM Schedule s
        JOIN s.user u
        LEFT JOIN ScheduleComment c ON s.id = c.schedule.id
        GROUP BY s.id, s.title, s.contents, u.name, s.createdAt, s.updatedAt
        ORDER BY s.updatedAt DESC
    """)
    Page<SchedulePageResponseDTO> findAllWithPage(Pageable pageable);
}
