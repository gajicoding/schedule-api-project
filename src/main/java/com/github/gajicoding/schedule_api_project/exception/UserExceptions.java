package com.github.gajicoding.schedule_api_project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserExceptions {
    private UserExceptions() {}

    public static ResponseStatusException notFoundById(Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id 의 유저가 존재하지 않습니다. : " + id);
    }
}
