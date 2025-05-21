package com.github.gajicoding.schedule_api_project.service;

import com.github.gajicoding.schedule_api_project.data.dto.schedule.ScheduleRequestDTO;
import com.github.gajicoding.schedule_api_project.data.dto.schedule.ScheduleResponseDTO;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDTO save(ScheduleRequestDTO requestDTO);

    ScheduleResponseDTO findById(Long id);

    List<ScheduleResponseDTO> findAll();

    ScheduleResponseDTO update(Long id, ScheduleRequestDTO requestDTO);

    void delete(Long id);
}
