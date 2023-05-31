package com.brvsk.studentmanagementsystemV2.controller;

import com.brvsk.studentmanagementsystemV2.model.dto.ExamDto;
import com.brvsk.studentmanagementsystemV2.model.dto.StudentDto;
import com.brvsk.studentmanagementsystemV2.model.entity.Student;
import com.brvsk.studentmanagementsystemV2.model.request.StudentRequest;
import com.brvsk.studentmanagementsystemV2.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public void createNewStudent(@RequestBody StudentRequest studentRequest){
        studentService.registerStudent(studentRequest);
    }

    @GetMapping
    public List<StudentDto> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping
    @RequestMapping("/{studentId}/exams")
    public List<ExamDto> getStudentExams(@PathVariable Long studentId){
        return studentService.getStudentsExam(studentId);
    }
}
