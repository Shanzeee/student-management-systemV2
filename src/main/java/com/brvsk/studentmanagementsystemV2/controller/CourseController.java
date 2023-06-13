package com.brvsk.studentmanagementsystemV2.controller;

import com.brvsk.studentmanagementsystemV2.model.dto.CourseDto;
import com.brvsk.studentmanagementsystemV2.model.request.CourseRequest;
import com.brvsk.studentmanagementsystemV2.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public List<CourseDto> getAllCourses(){
        return courseService.getAllCourses();
    }

    @PostMapping
    public ResponseEntity<String> createNewCourse(@Valid @RequestBody CourseRequest courseRequest){
        courseService.addCourse(courseRequest);
        return new ResponseEntity<>("A new course " +courseRequest.getName()+ " has been added", HttpStatus.CREATED);
    }

}
