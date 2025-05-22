package com.github.gajicoding.schedule_api_project.api.v1.service.impl;

import com.github.gajicoding.schedule_api_project.api.v1.data.dto.user.UserLoginRequestDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.user.UserResponseDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.user.UserSignUpRequestDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.entity.User;
import com.github.gajicoding.schedule_api_project.api.v1.exception.factory.UserExceptionFactory;
import com.github.gajicoding.schedule_api_project.api.v1.repository.UserRepository;
import com.github.gajicoding.schedule_api_project.api.v1.service.AuthService;
import com.github.gajicoding.schedule_api_project.common.security.PasswordEncryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncryptor passwordEncryptor;

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
