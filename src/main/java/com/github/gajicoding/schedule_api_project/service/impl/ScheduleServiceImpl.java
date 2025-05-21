package com.github.gajicoding.schedule_api_project.service.impl;

import com.github.gajicoding.schedule_api_project.data.dto.schedule.ScheduleRequestDTO;
import com.github.gajicoding.schedule_api_project.data.dto.schedule.ScheduleResponseDTO;
import com.github.gajicoding.schedule_api_project.data.entity.Schedule;
import com.github.gajicoding.schedule_api_project.data.entity.User;
import com.github.gajicoding.schedule_api_project.exception.ScheduleExceptions;
import com.github.gajicoding.schedule_api_project.exception.UserExceptions;
import com.github.gajicoding.schedule_api_project.repository.ScheduleRepository;
import com.github.gajicoding.schedule_api_project.repository.UserRepository;
import com.github.gajicoding.schedule_api_project.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Override
    public ScheduleResponseDTO save(ScheduleRequestDTO requestDTO) {
        Schedule schedule = scheduleRepository.save(requestDTO.toEntity());

        Long userId = schedule.getUser().getId();
        User user = userRepository.findById(userId).orElseThrow(() -> UserExceptions.notFoundById(userId));
        schedule.setUser(user);

        return new ScheduleResponseDTO(schedule);
    }

    @Override
    public ScheduleResponseDTO findById(Long id) {
        return new ScheduleResponseDTO(scheduleRepository.findById(id).orElseThrow(()-> ScheduleExceptions.notFoundById(id)));
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
    public ScheduleResponseDTO update(Long id, ScheduleRequestDTO requestDTO) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(()-> ScheduleExceptions.notFoundById(id));
        schedule.update(requestDTO);
        return new ScheduleResponseDTO(schedule);
    }

    @Override
    public void delete(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(()-> ScheduleExceptions.notFoundById(id));
        scheduleRepository.delete(schedule);
    }
}
