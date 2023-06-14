package com.brvsk.studentmanagementsystemV2.mapper;

import com.brvsk.studentmanagementsystemV2.model.dto.AnnouncementDto;
import com.brvsk.studentmanagementsystemV2.model.entity.Announcement;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnnouncementMapper {

    public AnnouncementDto toDto(Announcement announcement){
        return AnnouncementDto.builder()
                .title(announcement.getTitle())
                .message(announcement.getMessage())
                .announcementType(announcement.getAnnouncementType())
                .teacherName(announcement.getCreatedBy().getFirstName() +" "+ announcement.getCreatedBy().getLastName())
                .postedAt(announcement.getPostedAt())
                .build();
    }

    public List<AnnouncementDto> toDtoList (List<Announcement> announcementList){
        return announcementList
                .stream()
                .map(this::toDto)
                .toList();
    }
}
