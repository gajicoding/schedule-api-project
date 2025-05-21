package com.github.gajicoding.schedule_api_project.data.dto.schedule;

import com.github.gajicoding.schedule_api_project.data.entity.Schedule;
import com.github.gajicoding.schedule_api_project.data.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleRequestDTO {
    private String title;
    private String contents;
    private Long userId;

    public Schedule toEntity() {
        return Schedule.builder()
                .title(title)
                .contents(contents)
                .user(new User(userId))
                .build();
    }
}
