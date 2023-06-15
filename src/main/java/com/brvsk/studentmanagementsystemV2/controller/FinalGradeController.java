package com.brvsk.studentmanagementsystemV2.controller;

import com.brvsk.studentmanagementsystemV2.model.dto.FinalGradeDto;
import com.brvsk.studentmanagementsystemV2.model.request.FinalGradeRequest;
import com.brvsk.studentmanagementsystemV2.service.FinalGradeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/final-grades")
@AllArgsConstructor
public class FinalGradeController {

    private final FinalGradeService finalGradeService;

    @GetMapping
    @RequestMapping("/{studentId}")
    public List<FinalGradeDto> getStudentFinalGrades(@PathVariable Long studentId){
        return finalGradeService.getStudentFinalGrades(studentId);
    }

    @PostMapping
    public void addFinalGradeToStudent(@RequestBody FinalGradeRequest finalGradeRequest){
        finalGradeService.addFinalGrade(finalGradeRequest);
    }
}

