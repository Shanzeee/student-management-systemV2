package com.brvsk.studentmanagementsystemV2.controller;

import com.brvsk.studentmanagementsystemV2.model.entity.Student;
import com.brvsk.studentmanagementsystemV2.model.request.StudentRequest;
import com.brvsk.studentmanagementsystemV2.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public void createNewStudent(@RequestBody StudentRequest studentRequest){
        studentService.addStudent(studentRequest);
    }
}
