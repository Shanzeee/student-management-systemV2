package com.brvsk.studentmanagementsystemV2.model.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.function.LongFunction;

@Data
@Builder
public class ExamRequest {

    @NotNull
    private Long courseId;
    @NotNull
    private LocalDateTime startsAt;
    @NotNull
    private String name;
}
