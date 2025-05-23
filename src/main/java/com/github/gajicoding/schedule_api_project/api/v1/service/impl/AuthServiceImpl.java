package com.github.gajicoding.schedule_api_project.api.v1.service.impl;

import com.github.gajicoding.schedule_api_project.api.v1.data.dto.user.UserLoginRequestDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.user.UserResponseDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.user.UserSignUpRequestDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.entity.User;
import com.github.gajicoding.schedule_api_project.api.v1.exception.factory.UserExceptionFactory;
import com.github.gajicoding.schedule_api_project.api.v1.repository.UserRepository;
import com.github.gajicoding.schedule_api_project.api.v1.service.AuthService;
import com.github.gajicoding.schedule_api_project.common.security.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO signup(UserSignUpRequestDTO requestDTO) {
        Optional<User> existingUser = userRepository.findUserByEmail(requestDTO.getEmail());

        // 이메일 중복 체크
        if(existingUser.isPresent()) {
            throw UserExceptionFactory.emailAlreadyExists(requestDTO.getEmail());
        }

        User user = requestDTO.toEntity();

        // 비밀번호 암호화 후 저장
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return new UserResponseDTO(userRepository.save(user));
    }

    @Override
    public UserResponseDTO login(UserLoginRequestDTO requestDTO) {
        // 이메일로 유저 찾기
        User user = userRepository.findUserByEmail(requestDTO.getEmail()).orElseThrow(UserExceptionFactory::loginFailed);

        // 비밀번호 일치 확인
        if(!passwordEncoder.matches(requestDTO.getPassword(), user.getPassword())) {
            throw UserExceptionFactory.loginFailed();
        }

        return new UserResponseDTO(user);
    }
}
