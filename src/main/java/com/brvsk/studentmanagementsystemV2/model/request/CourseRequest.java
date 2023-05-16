package com.brvsk.studentmanagementsystemV2.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
public class CourseRequest {

    @NotBlank
    public String name;
    @NotNull
    public Long groupId;
    @NotNull
    public Long teacherId;
}
