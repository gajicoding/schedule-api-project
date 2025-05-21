package com.github.gajicoding.schedule_api_project.service.impl;

import com.github.gajicoding.schedule_api_project.data.dto.user.UserRequestDTO;
import com.github.gajicoding.schedule_api_project.data.dto.user.UserResponseDTO;
import com.github.gajicoding.schedule_api_project.data.entity.User;
import com.github.gajicoding.schedule_api_project.exception.UserExceptions;
import com.github.gajicoding.schedule_api_project.repository.UserRepository;
import com.github.gajicoding.schedule_api_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponseDTO create(UserRequestDTO requestDTO) {
        return new UserResponseDTO(userRepository.save(requestDTO.toEntity()));
    }

    @Override
    public UserResponseDTO findById(Long id) {
        return new UserResponseDTO(userRepository.findById(id).orElseThrow(() -> UserExceptions.notFoundById(id)));
    }

    @Override
    public List<UserResponseDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDTO::new)
                .toList();
    }

    @Transactional
    @Override
    public UserResponseDTO update(Long id, UserRequestDTO requestDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> UserExceptions.notFoundById(id)); // 유저 체크
        user.update(requestDTO);

        return new UserResponseDTO(user);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> UserExceptions.notFoundById(id)); // 유저 체크
        userRepository.delete(user);
    }
}
