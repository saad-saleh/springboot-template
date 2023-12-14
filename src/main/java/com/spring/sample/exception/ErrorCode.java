package com.spring.sample.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode{

    SERVER_ERROR("Q-001", "Unknown Technical error occurred.",HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_INPUT("Q-002", "The input data is invalid.",HttpStatus.BAD_REQUEST),
    NOT_FOUND("Q-003", "The requested data not found.",HttpStatus.NOT_FOUND)
    ;
    private String code;
    private String errorCodeDescription;
    private HttpStatus httpStatus;
}
