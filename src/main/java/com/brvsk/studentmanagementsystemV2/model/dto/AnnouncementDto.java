package com.brvsk.studentmanagementsystemV2.model.dto;

import com.brvsk.studentmanagementsystemV2.model.entity.AnnouncementType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
public class AnnouncementDto {

    private String title;
    private String message;
    private AnnouncementType announcementType;
    private String teacherName;
    private LocalDateTime postedAt;

}
