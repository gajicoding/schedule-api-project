package com.github.gajicoding.schedule_api_project.api.v1.controller;

import com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule_comment.ScheduleCommentRequestDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule_comment.ScheduleCommentResponseDTO;
import com.github.gajicoding.schedule_api_project.api.v1.service.ScheduleCommentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules/{scheduleId}/comments")
@RequiredArgsConstructor
public class ScheduleCommentController {
    private final ScheduleCommentService scheduleCommentService;

    /* Lv 7. 댓글 CRUD */
    @PostMapping
    public ResponseEntity<ScheduleCommentResponseDTO> save(@PathVariable Long scheduleId, @RequestBody ScheduleCommentRequestDTO requestDTO, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId"); // 세션 활용
        return new ResponseEntity<>(scheduleCommentService.save(scheduleId, userId, requestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleCommentResponseDTO> findById(@PathVariable("scheduleId") Long ignoredScheduleId, @PathVariable Long id) {
        return new ResponseEntity<>(scheduleCommentService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleCommentResponseDTO>> findAll(@PathVariable Long scheduleId) {
        return new ResponseEntity<>(scheduleCommentService.findAll(scheduleId), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleCommentResponseDTO> updateContents(@PathVariable("scheduleId") Long ignoredScheduleId, @PathVariable Long id, @RequestBody ScheduleCommentRequestDTO requestDTO) {
        return new ResponseEntity<>(scheduleCommentService.updateContents(id, requestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("scheduleId") Long ignoredScheduleId, @PathVariable("id") Long id) {
        scheduleCommentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
