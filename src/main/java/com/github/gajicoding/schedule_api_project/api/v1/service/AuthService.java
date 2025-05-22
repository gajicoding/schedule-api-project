package com.github.gajicoding.schedule_api_project.api.v1.service;

import com.github.gajicoding.schedule_api_project.api.v1.data.dto.user.UserLoginRequestDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.user.UserResponseDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.user.UserSignUpRequestDTO;

public interface AuthService {
    UserResponseDTO signup(UserSignUpRequestDTO requestDTO);

    UserResponseDTO login(UserLoginRequestDTO requestDTO);
}
