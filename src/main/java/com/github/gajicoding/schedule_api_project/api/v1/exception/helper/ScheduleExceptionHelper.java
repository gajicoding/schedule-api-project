package com.github.gajicoding.schedule_api_project.api.v1.exception.helper;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ScheduleExceptionHelper {
    private ScheduleExceptionHelper() {}

    public static ResponseStatusException notFoundById(Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id 의 일정이 존재하지 않습니다. : " + id);
    }

    public static ResponseStatusException noPermissionToUpdate() {
        return new ResponseStatusException(HttpStatus.FORBIDDEN, "해당 일정에 대한 수정 권한이 없습니다.");
    }

    public static ResponseStatusException noPermissionToDelete() {
        return new ResponseStatusException(HttpStatus.FORBIDDEN, "해당 일정에 대한 삭제 권한이 없습니다.");
    }
}
