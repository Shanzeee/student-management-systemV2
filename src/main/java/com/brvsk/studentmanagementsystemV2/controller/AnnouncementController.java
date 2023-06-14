package com.brvsk.studentmanagementsystemV2.controller;

import com.brvsk.studentmanagementsystemV2.model.dto.AnnouncementDto;
import com.brvsk.studentmanagementsystemV2.model.dto.CourseDto;
import com.brvsk.studentmanagementsystemV2.model.request.AnnouncementRequest;
import com.brvsk.studentmanagementsystemV2.model.request.CourseRequest;
import com.brvsk.studentmanagementsystemV2.service.AnnouncementService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/announcements")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @GetMapping
    public List<AnnouncementDto> findAnnouncementsForCourse(@RequestParam Long courseId){
        return announcementService.findAnnouncementsForCourse(courseId);
    }

    @PostMapping
    public ResponseEntity<String> addAnnouncement(@Valid @RequestBody AnnouncementRequest announcementRequest){
        announcementService.addAnnouncement(announcementRequest);
        return new ResponseEntity<>
                ("A new announcement has been added to group with id "+ announcementRequest.getCourseId() +" by teacher with id: " + announcementRequest.getAuthorId(), HttpStatus.CREATED);
    }

    @DeleteMapping
    public void deleteAnnouncement(@RequestParam Long announcementId){
        announcementService.deleteAnnouncement(announcementId);
    }

}
