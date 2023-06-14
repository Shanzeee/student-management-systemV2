package com.brvsk.studentmanagementsystemV2.service;


import com.brvsk.studentmanagementsystemV2.email.EmailSender;
import com.brvsk.studentmanagementsystemV2.exception.notFound.AnnouncementNotFoundException;
import com.brvsk.studentmanagementsystemV2.exception.notFound.CourseNotFoundException;
import com.brvsk.studentmanagementsystemV2.exception.notFound.TeacherNotFoundException;
import com.brvsk.studentmanagementsystemV2.mapper.AnnouncementMapper;
import com.brvsk.studentmanagementsystemV2.model.dto.AnnouncementDto;
import com.brvsk.studentmanagementsystemV2.model.entity.Announcement;
import com.brvsk.studentmanagementsystemV2.model.entity.AnnouncementType;
import com.brvsk.studentmanagementsystemV2.model.entity.Course;
import com.brvsk.studentmanagementsystemV2.model.entity.Teacher;
import com.brvsk.studentmanagementsystemV2.model.request.AnnouncementRequest;
import com.brvsk.studentmanagementsystemV2.repository.AnnouncementRepository;
import com.brvsk.studentmanagementsystemV2.repository.CourseRepository;
import com.brvsk.studentmanagementsystemV2.repository.TeacherRepository;
import com.brvsk.studentmanagementsystemV2.utils.AnnouncementComparator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private final EmailSender emailSender;
    private final AnnouncementMapper announcementMapper;

    @Transactional
    public void addAnnouncement(AnnouncementRequest announcementRequest){
        Teacher teacher = teacherRepository.findById(announcementRequest.getAuthorId())
                .orElseThrow(() -> new TeacherNotFoundException(announcementRequest.getAuthorId()));

        Course course = courseRepository.findById(announcementRequest.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException(announcementRequest.getCourseId()));

        Announcement announcement = toDto(announcementRequest);
        announcement.setCreatedBy(teacher);
        announcement.setCourse(course);
        course.getAnnouncements().add(announcement);
        announcementRepository.save(announcement);
        courseRepository.save(course);

        if (isAnnouncementImportant(announcement.getAnnouncementType())){
            course.getGroup()
                    .getStudents()
                    .forEach(student -> emailSender.send(
                            student.getEmail(),
                            "important announcement on the "+course.getName()+ " course website",
                            buildEmail(student.getFirstName(), course.getName())
                    ));
        }
    }

    public List<AnnouncementDto> findAnnouncementsForCourse(Long courseId){
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));

        List<Announcement> announcementList = course.getAnnouncements();
        announcementList.sort(new AnnouncementComparator());

        return  announcementList.stream()
                        .map(announcementMapper::toDto)
//                        .sorted(Comparator.comparing(a -> {
//                            if (isAnnouncementImportant(a.getAnnouncementType())){
//                                return  0;
//                            } else return 1;
//                        }))
                        .toList();

    }

    @Transactional
    public void deleteAnnouncement(Long announcementId){
        announcementRepository.findById(announcementId)
                .orElseThrow(() -> new AnnouncementNotFoundException(announcementId));

        announcementRepository.deleteById(announcementId);
    }

    private Announcement toDto (AnnouncementRequest announcementRequest){
        return Announcement.builder()
                .title(announcementRequest.getTitle())
                .message(announcementRequest.getMessage())
                .announcementType(announcementRequest.getAnnouncementType())
                .build();
    }

    private String buildEmail(String studentName, String courseName) {
        return "Hello " + studentName +
                " \n you have received an important announcement on the "+ courseName +" course website";
    }

    private boolean isAnnouncementImportant(AnnouncementType announcementType){
        return announcementType.equals(AnnouncementType.IMPORTANT_INFO) ||
                announcementType.equals(AnnouncementType.RESCHEDULING) ||
                announcementType.equals(AnnouncementType.EXAM);
    }
}
