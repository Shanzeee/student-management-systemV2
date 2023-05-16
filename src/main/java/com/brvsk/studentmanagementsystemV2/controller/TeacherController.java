package com.brvsk.studentmanagementsystemV2.controller;

import com.brvsk.studentmanagementsystemV2.model.dto.StudentDto;
import com.brvsk.studentmanagementsystemV2.model.dto.TeacherDto;
import com.brvsk.studentmanagementsystemV2.model.entity.Teacher;
import com.brvsk.studentmanagementsystemV2.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/teachers")
public class TeacherController {

    private final TeacherService teacherService;


    @PostMapping
    public Teacher createNewTeacher(@RequestBody Teacher teacher){
        return teacherService.addTeacher(teacher);
    }


    @GetMapping
    public List<TeacherDto> getAllTeachers(){
        return teacherService.getAllTeachers();
    }
}
