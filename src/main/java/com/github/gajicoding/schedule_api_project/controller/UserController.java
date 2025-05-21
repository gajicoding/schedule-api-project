package com.github.gajicoding.schedule_api_project.controller;

import com.github.gajicoding.schedule_api_project.data.dto.user.UserLoginRequestDTO;
import com.github.gajicoding.schedule_api_project.data.dto.user.UserRequestDTO;
import com.github.gajicoding.schedule_api_project.data.dto.user.UserResponseDTO;
import com.github.gajicoding.schedule_api_project.data.dto.user.UserSignUpRequestDTO;
import com.github.gajicoding.schedule_api_project.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /* Lv 2. 유저 CRUD */
    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO requestDTO) {
        return new ResponseEntity<>(userService.create(requestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody UserRequestDTO requestDTO) {
        return new ResponseEntity<>(userService.update(id, requestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /* Lv 3. 회원 가입 */
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> signup(@RequestBody UserSignUpRequestDTO requestDTO) {
        return new ResponseEntity<>(userService.signup(requestDTO), HttpStatus.OK);
    }

    /* Lv 4. 로그인 */
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserLoginRequestDTO requestDTO, HttpSession session) {
        UserResponseDTO userResponseDTO = userService.login(requestDTO);

        // 세션 저장
        session.setAttribute("userId", userResponseDTO.getId());

        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }
}
