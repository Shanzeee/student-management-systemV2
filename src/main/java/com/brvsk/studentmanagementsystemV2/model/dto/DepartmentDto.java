package com.brvsk.studentmanagementsystemV2.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class DepartmentDto {

    private Long id;
    private String name;
    private String shortcut;
    private List<String> groupsName;
}
