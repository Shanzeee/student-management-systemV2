package com.brvsk.studentmanagementsystemV2.service;

import com.brvsk.studentmanagementsystemV2.email.EmailSender;
import com.brvsk.studentmanagementsystemV2.exception.notFound.CourseNotFoundException;
import com.brvsk.studentmanagementsystemV2.exception.notFound.StudentNotFoundException;
import com.brvsk.studentmanagementsystemV2.mapper.ExamMapper;
import com.brvsk.studentmanagementsystemV2.model.dto.ExamDto;
import com.brvsk.studentmanagementsystemV2.model.entity.Course;
import com.brvsk.studentmanagementsystemV2.model.entity.Exam;
import com.brvsk.studentmanagementsystemV2.model.request.ExamRequest;
import com.brvsk.studentmanagementsystemV2.repository.CourseRepository;
import com.brvsk.studentmanagementsystemV2.repository.ExamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;
    private final CourseRepository courseRepository;
    private final ExamMapper examMapper;
    private final EmailSender emailSender;

    public void addExam(ExamRequest examRequest){
        Course course = courseRepository.findById(examRequest.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException(examRequest.getCourseId()));
        Exam newExam = toDto(examRequest);

        newExam.setCourse(course);
        examRepository.save(newExam);

        course.getGroup().getStudents()
                .forEach(student -> emailSender.send(
                        student.getEmail(),
                        "A new exam is scheduled",
                        buildExamInfoEmail(student.getFirstName(), newExam.getName())
                ));
    }

    public List<ExamDto> getAllExams(){
        return examRepository
                .findAll()
                .stream()
                .map(examMapper::toDto)
                .toList();
    }


    private Exam toDto(ExamRequest examRequest){
        return Exam.builder()
                .name(examRequest.getName())
                .startsAt(examRequest.getStartsAt())
                .build();
    }

    private String buildExamInfoEmail(String studentName, String examName) {
        return "Hello " + studentName +
                "\n A new exam "+ examName + " is scheduled \n" +
                "Check info at student-management-system app ;)";
    }

}
