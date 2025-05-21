package com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule;

import com.github.gajicoding.schedule_api_project.api.v1.data.dto.user.UserResponseDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDTO {
    private final Long id;
    private final String title;
    private final String contents;

    @Setter
    private UserResponseDTO user;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ScheduleResponseDTO(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();

        this.user = new UserResponseDTO(schedule.getUser());
    }

}
