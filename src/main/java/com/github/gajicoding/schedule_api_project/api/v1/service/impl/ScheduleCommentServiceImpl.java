package com.github.gajicoding.schedule_api_project.api.v1.service.impl;

import com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule_comment.ScheduleCommentRequestDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule_comment.ScheduleCommentResponseDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.entity.Schedule;
import com.github.gajicoding.schedule_api_project.api.v1.data.entity.ScheduleComment;
import com.github.gajicoding.schedule_api_project.api.v1.data.entity.User;
import com.github.gajicoding.schedule_api_project.api.v1.exception.helper.ScheduleCommentExceptionHelper;
import com.github.gajicoding.schedule_api_project.api.v1.exception.helper.ScheduleExceptionHelper;
import com.github.gajicoding.schedule_api_project.api.v1.exception.helper.UserExceptionHelper;
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
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()-> ScheduleExceptionHelper.notFoundById(scheduleId));
        User user = userRepository.findById(userId).orElseThrow(() -> UserExceptionHelper.notFoundById(userId));

        ScheduleComment scheduleComment = new ScheduleComment(requestDTO.getContents(), user, schedule);

        return new ScheduleCommentResponseDTO(scheduleCommentRepository.save(scheduleComment));
    }

    @Override
    public ScheduleCommentResponseDTO findById(Long id) {
        return new ScheduleCommentResponseDTO(scheduleCommentRepository.findById(id).orElseThrow(()-> ScheduleCommentExceptionHelper.notFoundById(id)));
    }

    @Override
    public List<ScheduleCommentResponseDTO> findAll(Long scheduleId) {
        return scheduleCommentRepository.findAllBySchedule_Id(scheduleId)
                .stream()
                .map(ScheduleCommentResponseDTO::new)
                .toList();
    }

    @Transactional
    @Override
    public ScheduleCommentResponseDTO updateContents(Long id, Long userId, ScheduleCommentRequestDTO requestDTO) {
        ScheduleComment scheduleComment = scheduleCommentRepository.findById(id).orElseThrow(()-> ScheduleCommentExceptionHelper.notFoundById(id));

        if(!userId.equals(scheduleComment.getUser().getId())){
            throw ScheduleCommentExceptionHelper.noPermissionToUpdate();
        }

        scheduleComment.setContents(requestDTO.getContents());

        // DB 에 변경 사항 강제 반영
        scheduleCommentRepository.flush();

        return new ScheduleCommentResponseDTO(scheduleComment);
    }

    @Override
    public void delete(Long id, Long userId) {
        ScheduleComment scheduleComment = scheduleCommentRepository.findById(id).orElseThrow(()-> ScheduleCommentExceptionHelper.notFoundById(id));

        if(!userId.equals(scheduleComment.getUser().getId())){
            throw ScheduleCommentExceptionHelper.noPermissionToDelete();
        }

        scheduleCommentRepository.delete(scheduleComment);
    }
}
