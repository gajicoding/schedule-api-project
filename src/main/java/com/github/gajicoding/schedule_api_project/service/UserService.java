package com.github.gajicoding.schedule_api_project.service;

import com.github.gajicoding.schedule_api_project.data.dto.user.UserRequestDTO;
import com.github.gajicoding.schedule_api_project.data.dto.user.UserResponseDTO;
import com.github.gajicoding.schedule_api_project.data.dto.user.UserSignUpRequestDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO create(UserRequestDTO requestDTO);

    UserResponseDTO findById(Long id);

    List<UserResponseDTO> findAll();

    UserResponseDTO update(Long id, UserRequestDTO requestDTO);

    void delete(Long id);

    UserResponseDTO signup(UserSignUpRequestDTO requestDTO);
}
