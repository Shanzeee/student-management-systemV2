package com.brvsk.studentmanagementsystemV2.controller;

import com.brvsk.studentmanagementsystemV2.model.request.ExamRequest;
import com.brvsk.studentmanagementsystemV2.service.ExamService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/exams")
@AllArgsConstructor
public class ExamController {

    private final ExamService examService;

    @PostMapping
    public void addExam(@RequestBody @Valid ExamRequest examRequest) {
        examService.addExam(examRequest);
    }
}
