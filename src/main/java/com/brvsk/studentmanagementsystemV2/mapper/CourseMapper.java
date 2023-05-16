package com.brvsk.studentmanagementsystemV2.mapper;

import com.brvsk.studentmanagementsystemV2.model.dto.CourseDto;
import com.brvsk.studentmanagementsystemV2.model.entity.Course;
import org.springframework.stereotype.Component;

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
}
