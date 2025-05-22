package com.github.gajicoding.schedule_api_project.api.v1.data.entity;

import com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule.ScheduleRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Schedules")
@Getter
@Builder
@AllArgsConstructor
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String contents;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Schedule() {
        // @Entity: no-arg 생성자가 포함되어야 함
    }

    public void update(ScheduleRequestDTO requestDTO) {
        this.title = requestDTO.getTitle();
        this.contents = requestDTO.getContents();
    }
}
