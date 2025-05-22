package com.github.gajicoding.schedule_api_project.api.v1.controller;


import com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule.SchedulePageResponseDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule.ScheduleRequestDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule.ScheduleResponseDTO;
import com.github.gajicoding.schedule_api_project.api.v1.service.ScheduleService;
import com.github.gajicoding.schedule_api_project.api.v1.validation.CreateGroup;
import com.github.gajicoding.schedule_api_project.api.v1.validation.UpdateGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 일정(Schedule) 관련 REST API 컨트롤러
 */
@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    /* Lv 1. 일정 CRUD */
    /**
     * 일정을 생성한다.
     *
     * @param userId 로그인된 사용자 ID. 세션에서 주입됨
     * @param requestDTO 생성할 일정 정보가 담긴 DTO (유효성 검사 적용)
     * @return 저장된 일정 정보가 담긴 DTO와 HTTP 상태 코드 201 (Created)
     */
    @PostMapping
    public ResponseEntity<ScheduleResponseDTO> save(@SessionAttribute Long userId, @RequestBody @Validated(CreateGroup.class) ScheduleRequestDTO requestDTO) {
        return new ResponseEntity<>(scheduleService.save(userId, requestDTO), HttpStatus.CREATED);
    }

    /**
     * ID로 일정을 조회한다.
     *
     * @param id 조회할 일정의 ID
     * @return 조회된 일정 정보가 담긴 DTO와 HTTP 상태 코드 200 (OK)
     */
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(scheduleService.findById(id), HttpStatus.OK);
    }

    /**
     * 모든 일정 목록을 조회한다.
     *
     * @return 모든 일정 정보가 담긴 DTO 리스트와 HTTP 상태 코드 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDTO>> findAll() {
        return new ResponseEntity<>(scheduleService.findAll(), HttpStatus.OK);
    }

    /**
     * 특정 일정 정보를 수정한다.
     *
     * @param id 수정할 일정의 ID
     * @param userId 로그인된 사용자 ID. 세션에서 주입됨
     * @param requestDTO 수정할 일정 정보가 담긴 DTO (유효성 검사 적용)
     * @return 수정된 일정 정보가 담긴 DTO와 HTTP 상태 코드 200 (OK)
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDTO> update(@PathVariable Long id, @SessionAttribute Long userId, @RequestBody @Validated(UpdateGroup.class) ScheduleRequestDTO requestDTO) {
        return new ResponseEntity<>(scheduleService.update(id, userId, requestDTO), HttpStatus.OK);
    }

    /**
     * 특정 일정을 삭제한다.
     *
     * @param id 삭제할 일정의 ID
     * @param userId 로그인된 사용자 ID. 세션에서 주입됨
     * @return HTTP 상태 코드 204 (No Content)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @SessionAttribute Long userId) {
        scheduleService.delete(id, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /* Lv 8. 일정 페이징 조회 */
    /**
     * 페이징을 적용하여 일정을 조회한다.
     *
     * @param pageable 페이징 및 정렬 정보 (페이지: 0, 크기: 10, 정렬: updatedAt 내림차순)
     * @return 페이징 처리된 일정 정보가 담긴 Page 객체와 HTTP 상태 코드 200 (OK)
     */
    @GetMapping("/pages")
    public ResponseEntity<Page<SchedulePageResponseDTO>> findAllPages(
            // page = 0, size = 10 는 디폴트 값
            // sort 값은 Entity 필드명 (DB 컬럼명 X)
            @PageableDefault(sort = "updatedAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return new ResponseEntity<>(scheduleService.findAllPages(pageable), HttpStatus.OK);
    }
}
