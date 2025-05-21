package com.github.gajicoding.schedule_api_project.api.v1.controller;


import com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule.SchedulePageResponseDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule.ScheduleRequestDTO;
import com.github.gajicoding.schedule_api_project.api.v1.data.dto.schedule.ScheduleResponseDTO;
import com.github.gajicoding.schedule_api_project.api.v1.service.ScheduleService;
import com.github.gajicoding.schedule_api_project.api.v1.validation.CreateGroup;
import com.github.gajicoding.schedule_api_project.api.v1.validation.UpdateGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    /* Lv 1. 일정 CRUD */
    @PostMapping
    public ResponseEntity<ScheduleResponseDTO> save(@RequestBody @Validated(CreateGroup.class) ScheduleRequestDTO requestDTO) {
        return new ResponseEntity<>(scheduleService.save(requestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(scheduleService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDTO>> findAll() {
        return new ResponseEntity<>(scheduleService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDTO> update(@PathVariable Long id, @RequestBody @Validated(UpdateGroup.class) ScheduleRequestDTO requestDTO) {
        return new ResponseEntity<>(scheduleService.update(id, requestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        scheduleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /* Lv 8. 일정 페이징 조회 */
    @GetMapping("/pages")
    public ResponseEntity<Page<SchedulePageResponseDTO>> findAllPages(
            // page = 0, size = 10 는 디폴트 값
            @PageableDefault(sort = "updatedAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return new ResponseEntity<>(scheduleService.findAllPages(pageable), HttpStatus.OK);
    }
}
