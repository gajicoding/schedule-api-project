package com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SchedulePageResponseDTO {
    private final Long id;
    private final String title;
    private final String contents;

    @Setter
    private Long commentCount;

    private final String userName;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
