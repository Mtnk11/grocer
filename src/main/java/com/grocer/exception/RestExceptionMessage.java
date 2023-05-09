package com.grocer.exception;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RestExceptionMessage {
    private int status;
    private RestErrorCode errorCode;
    private String message;
}
