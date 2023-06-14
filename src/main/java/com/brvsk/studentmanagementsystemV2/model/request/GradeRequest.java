package com.brvsk.studentmanagementsystemV2.model.request;

import com.brvsk.studentmanagementsystemV2.utils.Grade;
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
    @Grade
    private Double value;


}
