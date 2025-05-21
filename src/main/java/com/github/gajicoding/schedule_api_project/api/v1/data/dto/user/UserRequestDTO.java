package com.github.gajicoding.schedule_api_project.api.v1.data.dto.user;

import com.github.gajicoding.schedule_api_project.api.v1.data.entity.User;
import com.github.gajicoding.schedule_api_project.api.v1.validation.CreateGroup;
import com.github.gajicoding.schedule_api_project.api.v1.validation.UpdateGroup;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequestDTO {

    @NotNull(groups = {CreateGroup.class}, message = "이름이 입력되지 않았습니다.")
    @Size(groups = {CreateGroup.class, UpdateGroup.class}, max = 50, message = "제목은 최대 50자까지 입력 가능합니다.")
    private String name;

    @NotNull(groups = {CreateGroup.class}, message = "이메일이 입력되지 않았습니다.")
    @Size(groups = {CreateGroup.class, UpdateGroup.class}, max = 50, message = "이메일은 최대 100자까지 입력 가능합니다.")
    private String email;

    @NotNull(groups = {CreateGroup.class}, message = "비밀번호가 입력되지 않았습니다.")
    @Size(groups = {CreateGroup.class, UpdateGroup.class}, max = 50, message = "비밀번호는 최대 50자까지 입력 가능합니다.")
    private String password;

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
