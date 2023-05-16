package com.brvsk.studentmanagementsystemV2.service;

import com.brvsk.studentmanagementsystemV2.exception.notFound.GroupNotFoundException;
import com.brvsk.studentmanagementsystemV2.exception.notFound.TeacherNotFoundException;
import com.brvsk.studentmanagementsystemV2.mapper.CourseMapper;
import com.brvsk.studentmanagementsystemV2.model.dto.CourseDto;
import com.brvsk.studentmanagementsystemV2.model.entity.Course;
import com.brvsk.studentmanagementsystemV2.model.request.CourseRequest;
import com.brvsk.studentmanagementsystemV2.repository.CourseRepository;
import com.brvsk.studentmanagementsystemV2.repository.GroupRepository;
import com.brvsk.studentmanagementsystemV2.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final GroupRepository groupRepository;
    private final CourseMapper courseMapper;

    public List<CourseDto> getAllCourses(){
        return courseRepository
                .findAll()
                .stream()
                .map(courseMapper::toDto)
                .collect(Collectors.toList());
    }

    public void addCourse(CourseRequest courseRequest){
        var teacher = teacherRepository.findById(courseRequest.getTeacherId())
                .orElseThrow(() -> new TeacherNotFoundException(courseRequest.getTeacherId()));
        var group = groupRepository.findById(courseRequest.getGroupId())
                .orElseThrow(() -> new GroupNotFoundException(courseRequest.getGroupId()));

        Course newCourse = toEntity(courseRequest);

        newCourse.setTeacher(teacher);
        newCourse.setGroup(group);

        courseRepository.save(newCourse);
    }

    private Course toEntity(CourseRequest courseRequest){
        return Course.builder()
                .name(courseRequest.getName())
                .build();
    }


}
