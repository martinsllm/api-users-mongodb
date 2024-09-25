package com.example.api_users_mongodb.infraestructure.exceptions.handle;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.api_users_mongodb.infraestructure.exceptions.BusinessException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handlerBusinessException(BusinessException ex, HttpServletRequest request) {
        return response(ex.getMessage(), request, HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
    
    private ResponseEntity<ErrorResponse> response(final String message, final HttpServletRequest request, 
    final HttpStatus status, LocalDateTime data) {
        return ResponseEntity
            .status(status)
            .body(new ErrorResponse(message, data, status.value(), request.getRequestURI()));
    }
}
