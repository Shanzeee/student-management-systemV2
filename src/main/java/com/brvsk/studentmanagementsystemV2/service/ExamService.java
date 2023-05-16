package com.brvsk.studentmanagementsystemV2.service;

import com.brvsk.studentmanagementsystemV2.exception.notFound.CourseNotFoundException;
import com.brvsk.studentmanagementsystemV2.model.entity.Course;
import com.brvsk.studentmanagementsystemV2.model.entity.Exam;
import com.brvsk.studentmanagementsystemV2.model.request.ExamRequest;
import com.brvsk.studentmanagementsystemV2.repository.CourseRepository;
import com.brvsk.studentmanagementsystemV2.repository.ExamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;
    private final CourseRepository courseRepository;

    public void addExam(ExamRequest examRequest){
        Course course = courseRepository.findById(examRequest.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException(examRequest.getCourseId()));
        Exam newExam = toDto(examRequest);

        newExam.setCourse(course);
        examRepository.save(newExam);

    }

    private Exam toDto(ExamRequest examRequest){
        return Exam.builder()
                .name(examRequest.getName())
                .startsAt(examRequest.getStartsAt())
                .build();
    }
}
