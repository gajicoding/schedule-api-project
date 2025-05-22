package com.github.gajicoding.schedule_api_project.api.v1.controller;

import com.github.gajicoding.schedule_api_project.api.v1.data.dto.user.UserRequestDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.user.UserResponseDTO;
import com.github.gajicoding.schedule_api_project.api.v1.service.UserService;
import com.github.gajicoding.schedule_api_project.api.v1.validation.CreateGroup;
import com.github.gajicoding.schedule_api_project.api.v1.validation.UpdateGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 유저(User) 관련 REST API 컨트롤러
 * <p>
 * 경로는 {@code /users}
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /* Lv 2. 유저 CRUD */

    /**
     * 유저를 생성한다.
     *
     * @param requestDTO 생성할 유저 정보가 담긴 DTO (유효성 검사 적용)
     * @return @return 생성된 유저 정보가 담긴 DTO와 HTTP 상태 코드 201 (Created)
     */
    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Validated(CreateGroup.class) UserRequestDTO requestDTO) {
        return new ResponseEntity<>(userService.create(requestDTO), HttpStatus.CREATED);
    }

    /**
     * ID로 유저를 조회한다.
     *
     * @param id 조회할 유저의 ID
     * @return 조회된 유저 정보가 담긴 DTO와 HTTP 상태 코드 200 (OK)
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    /**
     * 모든 유저 목록을 조회한다.
     *
     * @return 모든 유저 정보가 담긴 DTO 리스트와 HTTP 상태 코드 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    /**
     * 특정 유저 정보를 수정한다.
     *
     * @param id 수정할 유저의 ID
     * @param requestDTO 수정할 유저 정보가 담긴 DTO (유효성 검사 적용)
     * @return 수정된 유저 정보가 담긴 DTO와 HTTP 상태 코드 200 (OK)
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody @Validated(UpdateGroup.class) UserRequestDTO requestDTO) {
        return new ResponseEntity<>(userService.update(id, requestDTO), HttpStatus.OK);
    }

    /**
     * 특정 유저를 삭제한다.
     *
     * @param id 삭제할 유저의 ID
     * @return HTTP 상태 코드 204 (No Content)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
