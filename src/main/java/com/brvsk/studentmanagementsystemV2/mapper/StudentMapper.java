package com.brvsk.studentmanagementsystemV2.mapper;

import com.brvsk.studentmanagementsystemV2.model.dto.CourseDto;
import com.brvsk.studentmanagementsystemV2.model.dto.StudentDto;
import com.brvsk.studentmanagementsystemV2.model.entity.Course;
import com.brvsk.studentmanagementsystemV2.model.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentMapper {

    public StudentDto toDto (Student student){
        return StudentDto.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .groupName(student.getGroup().getGroupName())
                .build();
    }

    public List<StudentDto> toListDto(List<Student> entities){
        return entities.stream()
                .map(this::toDto)
                .toList();
    }

    public CourseDto toCourseDto(Course course){
        return CourseDto.builder()
                .groupId(course.getId())
                .name(course.getName())
                .teacherName(course.getTeacher().getFirstName() + course.getTeacher().getLastName())
                .build();
    }
}
