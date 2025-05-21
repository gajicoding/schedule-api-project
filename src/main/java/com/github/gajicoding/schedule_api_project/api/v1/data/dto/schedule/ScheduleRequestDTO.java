package com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule;

import com.github.gajicoding.schedule_api_project.api.v1.data.entity.Schedule;
import com.github.gajicoding.schedule_api_project.api.v1.data.entity.User;
import com.github.gajicoding.schedule_api_project.api.v1.validation.CreateGroup;
import com.github.gajicoding.schedule_api_project.api.v1.validation.UpdateGroup;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import jakarta.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class ScheduleRequestDTO {

    @NotNull(groups = {CreateGroup.class}, message = "제목이 입력되지 않았습니다.")
    @Size(groups = {CreateGroup.class, UpdateGroup.class}, max = 100, message = "제목은 최대 100자까지 입력 가능합니다.")
    private String title;

    @NotNull(groups = {CreateGroup.class}, message = "내용이 입력되지 않았습니다.")
    @Size(groups = {CreateGroup.class, UpdateGroup.class}, max = 20000, message = "내용은 최대 20000자까지 입력 가능합니다.")
    private String contents;

    @NotNull(groups = {CreateGroup.class}, message = "작성자 ID가 입력되지 않았습니다.")
    private Long userId;

    public Schedule toEntity() {
        return Schedule.builder()
                .title(title)
                .contents(contents)
                .user(new User(userId))
                .build();
    }
}
