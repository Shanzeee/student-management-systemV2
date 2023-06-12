package com.brvsk.studentmanagementsystemV2.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CourseDto {

    private Long id;
    private String name;
    private Long groupId;
    private String teacherName;
}
