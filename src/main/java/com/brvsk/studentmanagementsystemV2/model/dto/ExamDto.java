package com.brvsk.studentmanagementsystemV2.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ExamDto {

    private Long id;
    private LocalDateTime startsAt;
    private String name;
    private String courseName;
    private Long courseId;
}
