package com.dahye.assignment.controller;

import com.dahye.assignment.dto.AssignmentRequestDto;
import com.dahye.assignment.dto.AssignmentResponseDto;
import com.dahye.assignment.service.AssignmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class AssignmentController {
    AssignmentService assignmentService;

    @PostMapping("/assignment")
    public AssignmentResponseDto getRequest(@RequestBody AssignmentRequestDto assignmentRequestDto) {
        return assignmentService.getResponse(assignmentRequestDto);
    }
}
