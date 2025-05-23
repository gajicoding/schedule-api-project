package com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.gajicoding.schedule_api_project.api.v1.data.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@JsonPropertyOrder({
        "id", "title", "contents", "commentCount", "userName", "createdAt", "updatedAt"
})
@Getter
public class SchedulePageResponseDTO {
    private final Long id;
    private final String title;
    private final String contents;

    private final Long commentCount;
    private final String userName;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public SchedulePageResponseDTO(Schedule schedule, Long commentCount, String userName) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();

        this.commentCount = commentCount;
        this.userName = userName;
    }
}