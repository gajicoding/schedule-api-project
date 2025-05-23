package com.github.gajicoding.schedule_api_project.api.v1.data.dto.user;

import com.github.gajicoding.schedule_api_project.api.v1.data.entity.User;
import com.github.gajicoding.schedule_api_project.api.v1.validation.CreateGroup;
import com.github.gajicoding.schedule_api_project.api.v1.validation.DeleteGroup;
import com.github.gajicoding.schedule_api_project.api.v1.validation.UpdateGroup;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserRequestDTO {

    @NotNull(groups = {CreateGroup.class, UpdateGroup.class}, message = "이름이 입력되지 않았습니다.")
    @Size(groups = {CreateGroup.class}, max = 50, message = "제목은 최대 50자까지 입력 가능합니다.")
    private String name;

    @NotNull(groups = {CreateGroup.class}, message = "이메일이 입력되지 않았습니다.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", groups = {CreateGroup.class}, message = "올바른 이메일 형식이 아닙니다.")
//    @Email (요구사항 - Pattern 사용으로 사용하지 않음)
    @Size(groups = {CreateGroup.class}, max = 50, message = "이메일은 최대 100자까지 입력 가능합니다.")
    private String email;

    @NotNull(groups = {CreateGroup.class, UpdateGroup.class, DeleteGroup.class}, message = "비밀번호가 입력되지 않았습니다.")
    @Size(groups = {CreateGroup.class}, max = 50, message = "비밀번호는 최대 50자까지 입력 가능합니다.")
    private String password;

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}