package com.github.gajicoding.schedule_api_project.api.v1.service;

import com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule_comment.ScheduleCommentRequestDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule_comment.ScheduleCommentResponseDTO;

import java.util.List;

public interface ScheduleCommentService {
    ScheduleCommentResponseDTO save(Long scheduleId, Long userId, ScheduleCommentRequestDTO requestDTO);

    ScheduleCommentResponseDTO findById(Long id);

    List<ScheduleCommentResponseDTO> findAll(Long scheduleId);

    ScheduleCommentResponseDTO updateContents(Long id, Long userId, ScheduleCommentRequestDTO requestDTO);

    void delete(Long id, Long userId);
}
