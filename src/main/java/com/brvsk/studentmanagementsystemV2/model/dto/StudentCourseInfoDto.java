package com.brvsk.studentmanagementsystemV2.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class StudentCourseInfoDto {
    private List<Double> grades;
    private double finalGrade;
}
