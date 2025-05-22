package com.github.gajicoding.schedule_api_project.api.v1.data.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginRequestDTO {

    private String email;

    private String password;
}
