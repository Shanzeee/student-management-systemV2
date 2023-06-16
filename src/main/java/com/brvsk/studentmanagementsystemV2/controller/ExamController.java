package com.brvsk.studentmanagementsystemV2.controller;

import com.brvsk.studentmanagementsystemV2.model.dto.StudentDto;
import com.brvsk.studentmanagementsystemV2.model.request.ExamRequest;
import com.brvsk.studentmanagementsystemV2.service.ExamService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/exams")
@AllArgsConstructor
public class ExamController {

    private final ExamService examService;

    @PostMapping
    public void addExam(@RequestBody @Valid ExamRequest examRequest) {
        examService.addExam(examRequest);
    }

    @GetMapping("/{examId}/students")
    public List<StudentDto> getExamStudents(@PathVariable Long examId) {
        return examService.getExamStudents(examId);
    }
}
