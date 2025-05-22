package com.github.gajicoding.schedule_api_project.api.v1.service.impl;

import com.github.gajicoding.schedule_api_project.common.security.PasswordEncryptor;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.user.UserLoginRequestDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.user.UserRequestDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.user.UserResponseDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.user.UserSignUpRequestDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.entity.User;
import com.github.gajicoding.schedule_api_project.api.v1.exception.factory.UserExceptionFactory;
import com.github.gajicoding.schedule_api_project.api.v1.repository.UserRepository;
import com.github.gajicoding.schedule_api_project.api.v1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncryptor passwordEncryptor;

    @Override
    public UserResponseDTO create(UserRequestDTO requestDTO) {
        return new UserResponseDTO(userRepository.save(requestDTO.toEntity()));
    }

    @Override
    public UserResponseDTO findById(Long id) {
        return new UserResponseDTO(userRepository.findById(id).orElseThrow(() -> UserExceptionFactory.notFoundById(id)));
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
        User user = userRepository.findById(id).orElseThrow(() -> UserExceptionFactory.notFoundById(id)); // 유저 체크
        user.update(requestDTO);

        return new UserResponseDTO(user);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> UserExceptionFactory.notFoundById(id)); // 유저 체크
        userRepository.delete(user);
    }

    @Override
    public UserResponseDTO signup(UserSignUpRequestDTO requestDTO) {
        User user = requestDTO.toEntity();
        user.setPassword(passwordEncryptor.encode(user.getPassword()));

        return new UserResponseDTO(userRepository.save(user));
    }

    @Override
    public UserResponseDTO login(UserLoginRequestDTO requestDTO) {
        User user = userRepository.findUserByEmail(requestDTO.getEmail()).orElseThrow(UserExceptionFactory::loginFailed);
        if(!passwordEncryptor.matches(requestDTO.getPassword(), user.getPassword())) {
            throw UserExceptionFactory.loginFailed();
        }
        return new UserResponseDTO(user);
    }


}
