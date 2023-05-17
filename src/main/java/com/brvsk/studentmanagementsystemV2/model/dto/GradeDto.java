package com.brvsk.studentmanagementsystemV2.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GradeDto {

    private Long id;
    private String courseName;
    private String examName;
    private double value;
    private String teacherName;
    private Long studentId;
}
