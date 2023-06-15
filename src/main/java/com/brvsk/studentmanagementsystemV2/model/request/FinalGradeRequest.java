package com.brvsk.studentmanagementsystemV2.model.request;

import com.brvsk.studentmanagementsystemV2.utils.Grade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
public class FinalGradeRequest {

    @NotNull
    private Long studentId;
    @NotNull
    private Long courseId;
    @Grade
    private Double value;
}


