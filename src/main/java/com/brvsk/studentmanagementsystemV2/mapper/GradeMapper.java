package com.brvsk.studentmanagementsystemV2.mapper;

import com.brvsk.studentmanagementsystemV2.model.dto.GradeDto;
import com.brvsk.studentmanagementsystemV2.model.entity.Grade;
import org.springframework.stereotype.Component;

@Component
public class GradeMapper {

    public GradeDto toDto (Grade grade){
        return GradeDto.builder()
                .id(grade.getId())
                .courseName(grade.getExam().getCourse().getName())
                .examName(grade.getExam().getName())
                .value(grade.getValue())
                .teacherName(grade.getExam().getCourse().getTeacher().getFirstName()
                        +" "+ grade.getExam().getCourse().getTeacher().getLastName())
                .studentId(grade.getStudent().getId())
                .build();
    }
}
