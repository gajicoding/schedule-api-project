package com.github.gajicoding.schedule_api_project.api.v1.controller;

import com.github.gajicoding.schedule_api_project.api.v1.data.dto.user.UserLoginRequestDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.user.UserResponseDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.user.UserSignUpRequestDTO;
import com.github.gajicoding.schedule_api_project.api.v1.service.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * 회원가입/로그인 관련 REST API 컨트롤러
 * <p>
 * 경로는 {@code /auth}
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    /* Lv 3. 회원 가입 */
    /**
     * 회원 가입을 처리한다.
     *
     * @param requestDTO 회원 가입에 필요한 정보가 담긴 DTO (유효성 검사 적용)
     * @return 가입된 유저 정보가 담긴 DTO와 HTTP 상태 코드 200 (OK)
     */
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> signup(@RequestBody @Validated UserSignUpRequestDTO requestDTO) {
        return new ResponseEntity<>(authService.signup(requestDTO), HttpStatus.OK);
    }

    /* Lv 4. 로그인 */
    /**
     * 로그인 요청을 처리한다.
     *
     * @param requestDTO 로그인에 필요한 정보가 담긴 DTO (유효성 검사 적용)
     * @param session HTTP 세션 객체 (로그인 성공 시 사용자 ID를 세션에 저장)
     * @return 로그인한 유저 정보가 담긴 DTO와 HTTP 상태 코드 200 (OK)
     */
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody @Validated UserLoginRequestDTO requestDTO, HttpSession session) {
        UserResponseDTO userResponseDTO = authService.login(requestDTO);

        // 세션 저장
        session.setAttribute("userId", userResponseDTO.getId());

        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }
}
