package com.techloom.EcomProductService.controllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * AOP -> GlobalControllerAdvice
 * Whenever there is NullPointerException in the code this code gets hit and shows the response pointed here.
 */
@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(Exception ex) {
        String exceptionResponse =
                "error : " + ex.getMessage() + ", code : " + HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.ok(exceptionResponse);
    }
}
