package com.dahye.assignment.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class AssignmentRequestDto {
    private String url;
    private String type;
    private String groupSize;
}
