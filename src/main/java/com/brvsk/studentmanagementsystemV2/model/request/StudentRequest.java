package com.brvsk.studentmanagementsystemV2.model.request;

import com.brvsk.studentmanagementsystemV2.model.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@Getter
public class StudentRequest {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private Long groupId;
}
