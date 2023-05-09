package com.grocer.exception;

import lombok.Getter;

@Getter
public class CustomRestException  extends RuntimeException{
    private final transient RestErrorCode restErrorCode;
    public CustomRestException(String message, RestErrorCode restErrorCode1) {
        super(message);
        this.restErrorCode = restErrorCode1;
    }

}