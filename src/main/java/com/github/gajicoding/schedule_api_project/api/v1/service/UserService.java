package com.github.gajicoding.schedule_api_project.api.v1.service;

import com.github.gajicoding.schedule_api_project.api.v1.data.dto.user.UserRequestDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.user.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO create(UserRequestDTO requestDTO);

    UserResponseDTO findById(Long id);

    List<UserResponseDTO> findAll();

    UserResponseDTO updateName(Long id, UserRequestDTO requestDTO);

    void delete(Long id, UserRequestDTO requestDTO);
}
