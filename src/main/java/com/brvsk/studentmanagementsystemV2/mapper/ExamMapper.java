package com.brvsk.studentmanagementsystemV2.mapper;

import com.brvsk.studentmanagementsystemV2.model.dto.ExamDto;
import com.brvsk.studentmanagementsystemV2.model.entity.Exam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExamMapper {

    public ExamDto toDto(Exam exam){
        return ExamDto.builder()
                .id(exam.getId())
                .startsAt(exam.getStartsAt())
                .name(exam.getName())
                .courseName(exam.getCourse().getName())
                .courseId(exam.getCourse().getId())
                .build();
    }

    public List<ExamDto> toListDto(List<Exam> entities){
        return entities.stream()
                .map(this::toDto)
                .toList();
    }



}




