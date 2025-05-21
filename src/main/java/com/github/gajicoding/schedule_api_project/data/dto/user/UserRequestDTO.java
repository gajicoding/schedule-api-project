package com.github.gajicoding.schedule_api_project.data.dto.user;

import com.github.gajicoding.schedule_api_project.data.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequestDTO {
    private String name;
    private String email;
    private String password;

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
