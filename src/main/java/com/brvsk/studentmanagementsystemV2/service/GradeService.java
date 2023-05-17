package com.brvsk.studentmanagementsystemV2.service;

import com.brvsk.studentmanagementsystemV2.exception.BadRequestException;
import com.brvsk.studentmanagementsystemV2.exception.notFound.ExamNotFoundException;
import com.brvsk.studentmanagementsystemV2.exception.notFound.NotFoundException;
import com.brvsk.studentmanagementsystemV2.exception.notFound.StudentNotFoundException;
import com.brvsk.studentmanagementsystemV2.model.entity.Exam;
import com.brvsk.studentmanagementsystemV2.model.entity.Grade;
import com.brvsk.studentmanagementsystemV2.model.entity.Student;
import com.brvsk.studentmanagementsystemV2.model.request.GradeRequest;
import com.brvsk.studentmanagementsystemV2.repository.*;
import jdk.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final ExamRepository examRepository;
    private final CourseRepository courseRepository;
    private final GroupRepository groupRepository;

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
    }

    private Grade toEntity (GradeRequest gradeRequest){
        return Grade.builder()
                .value(gradeRequest.getValue())
                .build();
    }
}
