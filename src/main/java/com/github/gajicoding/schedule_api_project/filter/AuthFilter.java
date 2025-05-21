package com.github.gajicoding.schedule_api_project.filter;

import com.github.gajicoding.schedule_api_project.exception.UserExceptions;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class AuthFilter implements Filter {
    private static final String[] WHITE_LIST = {"/users/signup", "/users/login"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();

        if(!isWhiteList(requestURI)) {
            HttpSession session = httpRequest.getSession(false);

            if(session == null || session.getAttribute("userId") == null) {
                throw UserExceptions.requiredSession();
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
