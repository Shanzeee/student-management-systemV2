package com.brvsk.studentmanagementsystemV2.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class GroupDto {

    private Long id;
    private String groupName;
    private List<String> studentsLastName;
    private List<String> coursesName;
}
