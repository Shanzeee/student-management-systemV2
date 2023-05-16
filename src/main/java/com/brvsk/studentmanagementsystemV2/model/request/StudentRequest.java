package com.brvsk.studentmanagementsystemV2.model.request;

import com.brvsk.studentmanagementsystemV2.model.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@Data
public class StudentRequest {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private Gender gender;
    @NotNull
    private Long groupId;
}
