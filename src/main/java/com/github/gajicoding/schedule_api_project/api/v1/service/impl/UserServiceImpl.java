package com.github.gajicoding.schedule_api_project.api.v1.service.impl;

import com.github.gajicoding.schedule_api_project.common.security.PasswordEncoder;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.user.UserRequestDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.user.UserResponseDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.entity.User;
import com.github.gajicoding.schedule_api_project.api.v1.exception.helper.UserExceptionHelper;
import com.github.gajicoding.schedule_api_project.api.v1.repository.UserRepository;
import com.github.gajicoding.schedule_api_project.api.v1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO create(UserRequestDTO requestDTO) {
        // FIXME: AuthService 의 signup() 메서드와 중복
        Optional<User> existingUser = userRepository.findUserByEmail(requestDTO.getEmail());

        // 이메일 중복 체크
        if(existingUser.isPresent()) {
            throw UserExceptionHelper.emailAlreadyExists(requestDTO.getEmail());
        }

        User user = requestDTO.toEntity();

        // 비밀번호 암호화 후 저장
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return new UserResponseDTO(userRepository.save(user));
    }

    @Override
    public UserResponseDTO findById(Long id) {
        return new UserResponseDTO(userRepository.findById(id).orElseThrow(() -> UserExceptionHelper.notFoundById(id)));
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
    public UserResponseDTO updateName(Long id, UserRequestDTO requestDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> UserExceptionHelper.notFoundById(id)); // 유저 체크

        // 비밀번호 일치 확인
        if(!passwordEncoder.matches(requestDTO.getPassword(), user.getPassword())) {
            throw UserExceptionHelper.invalidPassword();
        }

        // 이름 변경
        user.setName(requestDTO.getName());

        // DB 에 변경 사항 강제 반영
        userRepository.flush();

        return new UserResponseDTO(user);
    }

    @Override
    public void delete(Long id, UserRequestDTO requestDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> UserExceptionHelper.notFoundById(id)); // 유저 체크

        // 비밀번호 일치 확인
        if(!passwordEncoder.matches(requestDTO.getPassword(), user.getPassword())) {
            throw UserExceptionHelper.invalidPassword();
        }

        userRepository.delete(user);
    }
}
