package com.github.gajicoding.schedule_api_project.api.v1.exception.factory;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ScheduleExceptionFactory {
    private ScheduleExceptionFactory() {}

    public static ResponseStatusException notFoundById(Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id 의 일정이 존재하지 않습니다. : " + id);
    }
}
