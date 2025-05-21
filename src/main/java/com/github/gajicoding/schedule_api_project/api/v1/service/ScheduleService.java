package com.github.gajicoding.schedule_api_project.api.v1.service;

import com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule.SchedulePageResponseDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule.ScheduleRequestDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule.ScheduleResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDTO save(ScheduleRequestDTO requestDTO);

    ScheduleResponseDTO findById(Long id);

    List<ScheduleResponseDTO> findAll();

    ScheduleResponseDTO update(Long id, ScheduleRequestDTO requestDTO);

    void delete(Long id);

    Page<SchedulePageResponseDTO> findAllPages(Pageable pageable);
}
