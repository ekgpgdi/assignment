package com.dahye.assignment.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AssignmentRequestDto {
    private String url;
    private String type;
    private String groupSize;
}
