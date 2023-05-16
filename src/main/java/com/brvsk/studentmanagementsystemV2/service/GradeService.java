package com.brvsk.studentmanagementsystemV2.service;

import com.brvsk.studentmanagementsystemV2.exception.notFound.ExamNotFoundException;
import com.brvsk.studentmanagementsystemV2.exception.notFound.NotFoundException;
import com.brvsk.studentmanagementsystemV2.exception.notFound.StudentNotFoundException;
import com.brvsk.studentmanagementsystemV2.model.entity.Exam;
import com.brvsk.studentmanagementsystemV2.model.entity.Grade;
import com.brvsk.studentmanagementsystemV2.model.entity.Student;
import com.brvsk.studentmanagementsystemV2.model.request.GradeRequest;
import com.brvsk.studentmanagementsystemV2.repository.ExamRepository;
import com.brvsk.studentmanagementsystemV2.repository.GradeRepository;
import com.brvsk.studentmanagementsystemV2.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final ExamRepository examRepository;

    public void addGrade(GradeRequest gradeRequest){

        Student student = studentRepository.findById(gradeRequest.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException(gradeRequest.getStudentId()));

        Exam exam = examRepository.findById(gradeRequest.getExamId())
                .orElseThrow(() -> new ExamNotFoundException(gradeRequest.getExamId()));

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
