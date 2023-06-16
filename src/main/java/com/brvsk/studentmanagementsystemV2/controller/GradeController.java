package com.brvsk.studentmanagementsystemV2.controller;

import com.brvsk.studentmanagementsystemV2.model.dto.StudentCourseInfoDto;
import com.brvsk.studentmanagementsystemV2.model.request.GradeRequest;
import com.brvsk.studentmanagementsystemV2.service.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/grades")
@AllArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    @PostMapping
    public ResponseEntity<String> addGrade(@RequestBody @Valid GradeRequest gradeRequest) {
        gradeService.addGrade(gradeRequest);
        return new ResponseEntity<>("A new grade has been added", HttpStatus.CREATED);
    }

    @GetMapping("/stats")
    public StudentCourseInfoDto getStudentCourseStats(@RequestParam Long studentId, @RequestParam Long courseId) {
        return gradeService.getStudentCourseInfo(studentId, courseId);
    }
}