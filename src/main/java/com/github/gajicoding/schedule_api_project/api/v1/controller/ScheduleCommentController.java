package com.github.gajicoding.schedule_api_project.api.v1.controller;

import com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule_comment.ScheduleCommentRequestDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule_comment.ScheduleCommentResponseDTO;
import com.github.gajicoding.schedule_api_project.api.v1.service.ScheduleCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 일정(Schedule)의 댓글(ScheduleComment) 관련 REST API 컨트롤러
 */
@RestController
@RequestMapping("/schedules/{scheduleId}/comments")
@RequiredArgsConstructor
public class ScheduleCommentController {
    private final ScheduleCommentService scheduleCommentService;

    /* Lv 7. 댓글 CRUD */
    /**
     * 특정 일정의 댓글을 생성한다.
     *
     * @param scheduleId 댓글이 달릴 일정의 ID
     * @param userId 로그인된 사용자 ID. 세션에서 주입됨
     * @param requestDTO 생성할 댓글 정보가 담긴 DTO
     * @return 생성된 댓글 정보가 담긴 DTO와 HTTP 상태 코드 201 (Created)
     */
    @PostMapping
    public ResponseEntity<ScheduleCommentResponseDTO> save(
            @PathVariable Long scheduleId,
            @SessionAttribute Long userId,
            @RequestBody @Valid ScheduleCommentRequestDTO requestDTO
    ) {
        return new ResponseEntity<>(scheduleCommentService.save(scheduleId, userId, requestDTO), HttpStatus.CREATED);
    }

    /**
     * ID로 댓글을 조회한다.
     *
     * @param ignoredScheduleId (사용하지 않음)
     * @param id 조회할 댓글의 ID
     * @return 조회된 댓글 정보가 담긴 DTO와 HTTP 상태 코드 200 (OK)
     */
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleCommentResponseDTO> findById(
            @PathVariable("scheduleId") Long ignoredScheduleId,
            @PathVariable Long id
    ) {
        return new ResponseEntity<>(scheduleCommentService.findById(id), HttpStatus.OK);
    }

    /**
     * 특정 일정의 모든 댓글 목록을 조회한다.
     *
     * @param scheduleId 댓글 목록을 조회할 일정의 ID
     * @return 모든 댓글 정보가 담긴 DTO 리스트와 HTTP 상태 코드 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<ScheduleCommentResponseDTO>> findAll(@PathVariable Long scheduleId) {
        return new ResponseEntity<>(scheduleCommentService.findAll(scheduleId), HttpStatus.OK);
    }

    /**
     * 댓글의 내용을 수정한다.
     *
     * @param ignoredScheduleId (사용하지 않음)
     * @param id 수정할 댓글의 ID
     * @param userId 로그인된 사용자 ID. 세션에서 주입됨
     * @param requestDTO 수정할 댓글 정보가 담긴 DTO
     * @return 수정된 댓글 정보가 담긴 DTO와 HTTP 상태 코드 200 (OK)
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleCommentResponseDTO> updateContents(
            @PathVariable("scheduleId") Long ignoredScheduleId,
            @PathVariable Long id,
            @SessionAttribute Long userId,
            @RequestBody @Valid ScheduleCommentRequestDTO requestDTO
    ) {
        return new ResponseEntity<>(scheduleCommentService.updateContents(id, userId, requestDTO), HttpStatus.OK);
    }

    /**
     * 댓글을 삭제한다.
     *
     * @param ignoredScheduleId (사용하지 않음)
     * @param id 삭제할 댓글의 ID (경로 변수)
     * @param userId 로그인된 사용자 ID. 세션에서 주입됨
     * @return HTTP 상태 코드 204 (No Content)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("scheduleId") Long ignoredScheduleId,
            @SessionAttribute Long userId,
            @PathVariable("id") Long id
    ) {
        scheduleCommentService.delete(id, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
