package com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule;

import com.github.gajicoding.schedule_api_project.api.v1.data.entity.Schedule;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SchedulePageResponseDTO extends ScheduleResponseDTO {

    private Long commentCount;
    private String userName;

    public SchedulePageResponseDTO(Schedule schedule) {
        super(schedule);
    }
}