package com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule_comment;

import com.github.gajicoding.schedule_api_project.api.v1.validation.CreateGroup;
import com.github.gajicoding.schedule_api_project.api.v1.validation.UpdateGroup;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleCommentRequestDTO {
    @NotNull(groups = {CreateGroup.class}, message = "내용이 입력되지 않았습니다.")
    @Size(groups = {CreateGroup.class, UpdateGroup.class}, max = 2000, message = "내용은 최대 2000자까지 입력 가능합니다.")
    private String contents;
}