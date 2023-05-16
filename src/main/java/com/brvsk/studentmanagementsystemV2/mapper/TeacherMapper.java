package com.brvsk.studentmanagementsystemV2.mapper;

import com.brvsk.studentmanagementsystemV2.model.dto.StudentDto;
import com.brvsk.studentmanagementsystemV2.model.dto.TeacherDto;
import com.brvsk.studentmanagementsystemV2.model.entity.Student;
import com.brvsk.studentmanagementsystemV2.model.entity.Teacher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeacherMapper {
    public TeacherDto toDto (Teacher teacher){
        return TeacherDto.builder()
                .id(teacher.getId())
                .firstName(teacher.getFirstName())
                .lastName(teacher.getLastName())
                .email(teacher.getEmail())
                .gender(teacher.getGender())
                .build();
    }

    public List<TeacherDto>  toListDto(List<Teacher> entities){
        return entities.stream()
                .map(this::toDto)
                .toList();
    }
}

