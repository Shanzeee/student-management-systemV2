package com.brvsk.studentmanagementsystemV2.model.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FinalGradeDto {

    private String courseName;
    private String teacherName;
    private double value;
}
