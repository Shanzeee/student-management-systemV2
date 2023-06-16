package com.brvsk.studentmanagementsystemV2.service;

import com.brvsk.studentmanagementsystemV2.email.EmailSender;
import com.brvsk.studentmanagementsystemV2.exception.BadRequestException;
import com.brvsk.studentmanagementsystemV2.exception.notFound.CourseNotFoundException;
import com.brvsk.studentmanagementsystemV2.exception.notFound.ExamNotFoundException;
import com.brvsk.studentmanagementsystemV2.exception.notFound.StudentNotFoundException;
import com.brvsk.studentmanagementsystemV2.mapper.GradeMapper;
import com.brvsk.studentmanagementsystemV2.model.dto.StudentCourseInfoDto;
import com.brvsk.studentmanagementsystemV2.model.entity.Exam;
import com.brvsk.studentmanagementsystemV2.model.entity.FinalGrade;
import com.brvsk.studentmanagementsystemV2.model.entity.Grade;
import com.brvsk.studentmanagementsystemV2.model.entity.Student;
import com.brvsk.studentmanagementsystemV2.model.request.GradeRequest;
import com.brvsk.studentmanagementsystemV2.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final ExamRepository examRepository;
    private final GradeMapper gradeMapper;
    private final EmailSender emailSender;
    private final CourseRepository courseRepository;
    private final FinalGradeRepository finalGradeRepository;

    public void addGrade(GradeRequest gradeRequest){

        Student student = studentRepository.findById(gradeRequest.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException(gradeRequest.getStudentId()));

        Exam exam = examRepository.findById(gradeRequest.getExamId())
                .orElseThrow(() -> new ExamNotFoundException(gradeRequest.getExamId()));

        Long studentGroupId = student.getGroup().getId();
        Long examGroupId = exam.getCourse().getGroup().getId();

        if (studentGroupId != examGroupId){
            throw new BadRequestException("The student did not write this exam");
        }
        Grade newGrade = toEntity(gradeRequest);

        newGrade.setExam(exam);
        newGrade.setStudent(student);
        gradeRepository.save(newGrade);

        emailSender.send(
                student.getEmail(),
                "New grade for " + exam.getName() + " exam",
                buildGradeInfoEmail(student.getFirstName())
        );

    }

    public StudentCourseInfoDto getStudentCourseInfo(Long studentId, Long courseId){
        studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
        courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));

        FinalGrade finalGrade = finalGradeRepository.findByStudentIdAndCourseId(studentId, courseId);
        double finalGradeValue = 0.0;
        if (finalGrade.getValue() != null){
            finalGradeValue = finalGrade.getValue();
        }

        List<Double> studentGrades = gradeRepository.findAllByStudentIdAndExam_Course_Id(studentId, courseId)
                .stream()
                .map(Grade::getValue)
                .toList();
        return StudentCourseInfoDto.builder()
                .grades(studentGrades)
                .finalGrade(finalGradeValue)
                .build();
    }

    private Grade toEntity (GradeRequest gradeRequest){
        return Grade.builder()
                .value(gradeRequest.getValue())
                .build();
    }

    private String buildGradeInfoEmail(String studentName) {
        return "Hello " + studentName +
                " \n The teacher entered a new grade in the student-management-system application";
    }


}
