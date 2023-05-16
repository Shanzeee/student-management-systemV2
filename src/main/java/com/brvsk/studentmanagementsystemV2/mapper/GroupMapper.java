package com.brvsk.studentmanagementsystemV2.mapper;

import com.brvsk.studentmanagementsystemV2.model.dto.DepartmentDto;
import com.brvsk.studentmanagementsystemV2.model.dto.GroupDto;
import com.brvsk.studentmanagementsystemV2.model.entity.Course;
import com.brvsk.studentmanagementsystemV2.model.entity.Department;
import com.brvsk.studentmanagementsystemV2.model.entity.Group;
import com.brvsk.studentmanagementsystemV2.model.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupMapper {

    public GroupDto toDto(Group group){
        return GroupDto.builder()
                .id(group.getId())
                .groupName(group.getGroupName())
                .studentsLastName(getStudentsLastName(group))
                .coursesName(getCoursesName(group))
                .build();
    }

    private List<String> getStudentsLastName(Group group){
        return group.getStudents().stream()
                .map(Student::getLastName)
                .toList();
    }

    private List<String> getCoursesName(Group group){
        return group.getCourses().stream()
                .map(Course::getName)
                .toList();
    }
}
