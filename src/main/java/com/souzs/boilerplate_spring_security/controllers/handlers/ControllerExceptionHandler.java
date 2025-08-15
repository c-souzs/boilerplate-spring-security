package com.souzs.boilerplate_spring_security.controllers.handlers;

import com.souzs.boilerplate_spring_security.domain.dtos.CustomError;
import com.souzs.boilerplate_spring_security.exceptions.AuthException;
import com.souzs.boilerplate_spring_security.exceptions.EntityException;
import com.souzs.boilerplate_spring_security.exceptions.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<CustomError> auth(AuthException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        CustomError error = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(EntityException.class)
    public ResponseEntity<CustomError> entity(EntityException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomError error = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<CustomError> jwt(JwtException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        CustomError error = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }
}
