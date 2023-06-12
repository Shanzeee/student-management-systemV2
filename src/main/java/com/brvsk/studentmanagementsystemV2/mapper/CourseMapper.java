package com.brvsk.studentmanagementsystemV2.mapper;

import com.brvsk.studentmanagementsystemV2.model.dto.CourseDto;
import com.brvsk.studentmanagementsystemV2.model.dto.StudentDto;
import com.brvsk.studentmanagementsystemV2.model.entity.Course;
import com.brvsk.studentmanagementsystemV2.model.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseMapper {

    public CourseDto toDto(Course course){
        return CourseDto.builder()
                .id(course.getId())
                .name(course.getName())
                .groupId(course.getGroup().getId())
                .teacherName(course.getTeacher().getFirstName() +" "+ course.getTeacher().getLastName())
                .build();
    }

    public StudentDto toStudentDto(Student student){
        return StudentDto.builder()
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .groupName(student.getGroup().getGroupName())
                .id(student.getId())
                .build();
    }

    public List<CourseDto> toListDto(List<Course> courses) {
        return courses
                .stream()
                .map(this::toDto)
                .toList();
    }
}
