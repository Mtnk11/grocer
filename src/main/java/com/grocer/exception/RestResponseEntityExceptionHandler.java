package com.grocer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(CustomRestException.class)
    protected ResponseEntity<Object> handleCustomException(CustomRestException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        RestExceptionMessage restExceptionMessage = RestUtils.getRestExceptionMessage(
                e.getMessage(),
                e.getRestErrorCode(),
                status);
        return new ResponseEntity<>(restExceptionMessage,status);
    }

}