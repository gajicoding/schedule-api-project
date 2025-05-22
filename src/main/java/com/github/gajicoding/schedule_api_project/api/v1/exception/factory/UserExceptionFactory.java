package com.github.gajicoding.schedule_api_project.api.v1.exception.factory;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserExceptionFactory {
    private UserExceptionFactory() {}

    public static ResponseStatusException notFoundById(Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id 의 유저가 존재하지 않습니다. : " + id);
    }

    public static ResponseStatusException emailAlreadyExists(String email) {
        return new ResponseStatusException(HttpStatus.CONFLICT, "이미 존재하는 이메일 입니다. : " + email);
    }

    public static ResponseStatusException loginFailed() {
        return new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 일치하지 않습니다.");
    }

    public static ResponseStatusException requiredSession() {
        return new ResponseStatusException(HttpStatus.UNAUTHORIZED, "접근 권한이 없습니다. 로그인 후 이용해 주세요.");
    }
}
