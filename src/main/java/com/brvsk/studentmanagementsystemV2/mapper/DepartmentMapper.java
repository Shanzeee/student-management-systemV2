package com.brvsk.studentmanagementsystemV2.mapper;

import com.brvsk.studentmanagementsystemV2.model.dto.CourseDto;
import com.brvsk.studentmanagementsystemV2.model.dto.DepartmentDto;
import com.brvsk.studentmanagementsystemV2.model.entity.Course;
import com.brvsk.studentmanagementsystemV2.model.entity.Department;
import com.brvsk.studentmanagementsystemV2.model.entity.Group;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartmentMapper {


    public DepartmentDto toDto(Department department){
        return DepartmentDto.builder()
                .id(department.getId())
                .name(department.getName())
                .shortcut(department.getShortcut())
                .groupsName(getGroupsName(department))
                .build();
    }

    private List<String> getGroupsName(Department department){
        return department.getGroups().stream()
                .map(Group::getGroupName)
                .toList();
    }
}

