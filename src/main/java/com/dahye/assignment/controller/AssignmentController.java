package com.dahye.assignment.controller;

import com.dahye.assignment.dto.AssignmentRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AssignmentController {

    @PostMapping("/assignment")
    public String getRequest(@RequestBody AssignmentRequestDto assignmentRequestDto) {
        return "OK";
    }
}
