package com.github.gajicoding.schedule_api_project.api.v1.service.impl;

import com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule.SchedulePageResponseDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule.ScheduleRequestDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule.ScheduleResponseDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.entity.Schedule;
import com.github.gajicoding.schedule_api_project.api.v1.data.entity.User;
import com.github.gajicoding.schedule_api_project.api.v1.exception.factory.ScheduleExceptionFactory;
import com.github.gajicoding.schedule_api_project.api.v1.exception.factory.UserExceptionFactory;
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
    private final UserRepository userRepository;

    @Override
    public ScheduleResponseDTO save(ScheduleRequestDTO requestDTO) {
        Schedule schedule = scheduleRepository.save(requestDTO.toEntity());

        Long userId = schedule.getUser().getId();
        User user = userRepository.findById(userId).orElseThrow(() -> UserExceptionFactory.notFoundById(userId));
        schedule.setUser(user);

        return new ScheduleResponseDTO(schedule);
    }

    @Override
    public ScheduleResponseDTO findById(Long id) {
        return new ScheduleResponseDTO(scheduleRepository.findById(id).orElseThrow(()-> ScheduleExceptionFactory.notFoundById(id)));
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
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(()-> ScheduleExceptionFactory.notFoundById(id));
        schedule.update(requestDTO);

        scheduleRepository.flush(); // 변경 사항 강제 반영
        return new ScheduleResponseDTO(schedule);
    }

    @Override
    public void delete(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(()-> ScheduleExceptionFactory.notFoundById(id));
        scheduleRepository.delete(schedule);
    }

    @Override
    public Page<SchedulePageResponseDTO> findAllPages(Pageable pageable) {
        return scheduleRepository.findAllWithPage(pageable);
    }
}
