package com.github.gajicoding.schedule_api_project.api.v1.service.impl;

import com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule_comment.ScheduleCommentRequestDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule_comment.ScheduleCommentResponseDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.entity.Schedule;
import com.github.gajicoding.schedule_api_project.api.v1.data.entity.ScheduleComment;
import com.github.gajicoding.schedule_api_project.api.v1.data.entity.User;
import com.github.gajicoding.schedule_api_project.api.v1.exception.ScheduleCommentExceptions;
import com.github.gajicoding.schedule_api_project.api.v1.exception.ScheduleExceptions;
import com.github.gajicoding.schedule_api_project.api.v1.exception.UserExceptions;
import com.github.gajicoding.schedule_api_project.api.v1.repository.ScheduleCommentRepository;
import com.github.gajicoding.schedule_api_project.api.v1.repository.ScheduleRepository;
import com.github.gajicoding.schedule_api_project.api.v1.repository.UserRepository;
import com.github.gajicoding.schedule_api_project.api.v1.service.ScheduleCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleCommentServiceImpl implements ScheduleCommentService {
    private final ScheduleCommentRepository scheduleCommentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public ScheduleCommentResponseDTO save(Long scheduleId, Long userId, ScheduleCommentRequestDTO requestDTO) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()-> ScheduleExceptions.notFoundById(scheduleId));
        User user = userRepository.findById(userId).orElseThrow(() -> UserExceptions.notFoundById(userId));

        ScheduleComment scheduleComment = new ScheduleComment(requestDTO.getContents(), user, schedule);

        return new ScheduleCommentResponseDTO(scheduleCommentRepository.save(scheduleComment));
    }

    @Override
    public ScheduleCommentResponseDTO findById(Long id) {
        return new ScheduleCommentResponseDTO(scheduleCommentRepository.findById(id).orElseThrow(()-> ScheduleCommentExceptions.notFoundById(id)));
    }

    @Override
    public List<ScheduleCommentResponseDTO> findAll(Long scheduleId) {
        return scheduleCommentRepository.findAll()
                .stream()
                .map(ScheduleCommentResponseDTO::new)
                .toList();
    }

    @Transactional
    @Override
    public ScheduleCommentResponseDTO updateContents(Long id, ScheduleCommentRequestDTO requestDTO) {
        ScheduleComment scheduleComment = scheduleCommentRepository.findById(id).orElseThrow(()-> ScheduleCommentExceptions.notFoundById(id));
        scheduleComment.setContents(requestDTO.getContents());
        return new ScheduleCommentResponseDTO(scheduleComment);
    }

    @Override
    public void delete(Long id) {
        ScheduleComment scheduleComment = scheduleCommentRepository.findById(id).orElseThrow(()-> ScheduleCommentExceptions.notFoundById(id));
        scheduleCommentRepository.delete(scheduleComment);
    }
}
