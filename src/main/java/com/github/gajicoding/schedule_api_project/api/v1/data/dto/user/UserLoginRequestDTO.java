package com.github.gajicoding.schedule_api_project.api.v1.data.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserLoginRequestDTO {
    @NotNull
    private String email;

    @NotNull
    private String password;
}