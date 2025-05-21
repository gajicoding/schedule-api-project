package com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule_comment;

import com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule.ScheduleResponseDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.user.UserResponseDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.entity.ScheduleComment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleCommentResponseDTO {
    private final Long id;
    private final String contents;
    private final UserResponseDTO user;
    private final ScheduleResponseDTO schedule;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ScheduleCommentResponseDTO(ScheduleComment scheduleComment) {
        this.id = scheduleComment.getId();
        this.contents = scheduleComment.getContents();
        this.createdAt = scheduleComment.getCreatedAt();
        this.updatedAt = scheduleComment.getUpdatedAt();

        this.user = new UserResponseDTO(scheduleComment.getUser());
        this.schedule = new ScheduleResponseDTO(scheduleComment.getSchedule());
    }
}
