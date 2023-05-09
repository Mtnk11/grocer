package com.grocer.exception;

import org.springframework.http.HttpStatus;

public class RestUtils {
    public static RestExceptionMessage getRestExceptionMessage(String msg, RestErrorCode errorCode, HttpStatus status) {
        return RestExceptionMessage.builder()
                .message(msg)
                .errorCode(errorCode)
                .status(status.value())
                .build();
    }
}