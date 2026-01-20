package com.practise.httpsflow.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    PAYMENT_NOT_FOUND(HttpStatus.NOT_FOUND,"PAYMENT_NOT_FOUND"),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST,"INVALID_REQUEST"),
    INVALID_AMOUNT(HttpStatus.BAD_REQUEST,"INVALID_AMOUNT"),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"INTERNAL_ERROR");

    private final HttpStatus httpStatus;
    private final String defaultMessage;


    ErrorCode(HttpStatus httpStatus, String defaultMessage) {
        this.httpStatus = httpStatus;
        this.defaultMessage = defaultMessage;
    }
    public HttpStatus status(){
        return httpStatus;
    }
    public String getDefaultMessage(){
        return defaultMessage;
    }
}
