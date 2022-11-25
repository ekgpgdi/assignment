package com.dahye.assignment.exception;

import lombok.Getter;

@Getter
public class NotFoundUrlException extends RuntimeException {

    private ErrorCode errorCode;

    public NotFoundUrlException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}