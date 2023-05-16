package com.brvsk.studentmanagementsystemV2.model.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
@Builder
@Data
public class GradeRequest {

    @NotNull
    private Long studentId;
    @NotNull
    private Long examId;
    @NotNull
    private Double value;


}
