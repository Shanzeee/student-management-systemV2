package com.brvsk.studentmanagementsystemV2.model.request;

import com.brvsk.studentmanagementsystemV2.model.entity.AnnouncementType;
import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class AnnouncementRequest {

    @NotNull
    private Long courseId;

    @NotNull
    private Long authorId;

    @NotBlank
    private String title;

    @NotBlank
    private String message;

    @NotNull
    private AnnouncementType announcementType;


}
