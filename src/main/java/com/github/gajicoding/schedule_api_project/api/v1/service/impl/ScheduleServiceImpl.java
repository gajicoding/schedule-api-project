package com.github.gajicoding.schedule_api_project.api.v1.service.impl;

import com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule.SchedulePageResponseDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule.ScheduleRequestDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule.ScheduleResponseDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.entity.Schedule;
import com.github.gajicoding.schedule_api_project.api.v1.data.entity.User;
import com.github.gajicoding.schedule_api_project.api.v1.exception.helper.ScheduleExceptionHelper;
import com.github.gajicoding.schedule_api_project.api.v1.exception.helper.UserExceptionHelper;
import com.github.gajicoding.schedule_api_project.api.v1.repository.ScheduleCommentRepository;
import com.github.gajicoding.schedule_api_project.api.v1.repository.ScheduleRepository;
import com.github.gajicoding.schedule_api_project.api.v1.repository.UserRepository;
import com.github.gajicoding.schedule_api_project.api.v1.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleCommentRepository scheduleCommentRepository;
    private final UserRepository userRepository;

    @Override
    public ScheduleResponseDTO save(Long userId, ScheduleRequestDTO requestDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> UserExceptionHelper.notFoundById(userId));

        Schedule schedule = requestDTO.toEntity();
        schedule.setUser(user);

        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponseDTO(savedSchedule);
    }

    @Override
    public ScheduleResponseDTO findById(Long id) {
        return new ScheduleResponseDTO(scheduleRepository.findById(id).orElseThrow(()-> ScheduleExceptionHelper.notFoundById(id)));
    }

    @Override
    public List<ScheduleResponseDTO> findAll() {
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDTO::new)
                .toList();
    }

    @Transactional
    @Override
    public ScheduleResponseDTO update(Long id, Long userId, ScheduleRequestDTO requestDTO) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(()-> ScheduleExceptionHelper.notFoundById(id));

        if(!userId.equals(schedule.getUser().getId())){
            throw ScheduleExceptionHelper.noPermissionToUpdate();
        }

        schedule.update(requestDTO);

        // DB 에 변경 사항 강제 반영
        userRepository.flush();

        return new ScheduleResponseDTO(schedule);
    }

    @Override
    public void delete(Long id, Long userId) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(()-> ScheduleExceptionHelper.notFoundById(id));

        if(!userId.equals(schedule.getUser().getId())){
            throw ScheduleExceptionHelper.noPermissionToDelete();
        }

        scheduleRepository.delete(schedule);
    }

    @Override
    public Page<SchedulePageResponseDTO> findAllPages(Pageable pageable) {
        Page<Schedule> schedules = scheduleRepository.findAll(pageable);

        return schedules.map(schedule -> {
            Long count = scheduleCommentRepository.countScheduleCommentBySchedule_Id(schedule.getUser().getId());
            String userName = schedule.getUser().getName();
            return new SchedulePageResponseDTO(schedule, count, userName);
        });
    }
}
