package com.grocer.exception;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class RestResponseEntityException extends RuntimeException implements Serializable {

    private transient final RestErrorCode restErrorCode;

    RestResponseEntityException(String message) {
        super(message);
        this.restErrorCode = RestErrorCode.default_rest_error;
    }

    RestResponseEntityException(String message, RestErrorCode restErrorCode) {
        super(message);
        this.restErrorCode = restErrorCode;
    }
}