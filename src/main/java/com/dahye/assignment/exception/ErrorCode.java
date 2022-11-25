package com.dahye.assignment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    NOT_FOUND_URL(404,"COMMON-ERR-404","URL NOT FOUND"),
    INTER_SERVER_ERROR(500,"COMMON-ERR-500","INTER SERVER ERROR");

    private int status;
    private String errorCode;
    private String message;
}
