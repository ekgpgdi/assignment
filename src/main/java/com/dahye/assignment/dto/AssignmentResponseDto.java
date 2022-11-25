package com.dahye.assignment.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AssignmentResponseDto {
    private String quotient;
    private String remainder;
}
