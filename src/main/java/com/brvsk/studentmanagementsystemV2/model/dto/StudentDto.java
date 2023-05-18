package com.brvsk.studentmanagementsystemV2.model.dto;

import com.brvsk.studentmanagementsystemV2.model.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class StudentDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private String groupName;
}
