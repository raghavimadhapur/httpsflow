package com.practise.httpsflow.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handle(ApiException ex){
        return ResponseEntity.status(ex.getErrorCode().status()).
                body(new ErrorResponse(ex.getErrorCode().name(),
                        ex.getErrorCode().getDefaultMessage(),
                        Instant.now()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleOther(Exception ex){
        return ResponseEntity.status(ErrorCode.INTERNAL_ERROR.status()).
                body(new ErrorResponse(ErrorCode.INTERNAL_ERROR.name(), "Internal server error" ,Instant.now()));
    }
}
