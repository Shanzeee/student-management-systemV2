package com.brvsk.studentmanagementsystemV2.repository;


import com.brvsk.studentmanagementsystemV2.model.entity.Exam;
import com.brvsk.studentmanagementsystemV2.model.entity.Grade;
import com.brvsk.studentmanagementsystemV2.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade,Long> {

    List<Grade> findAllByStudentAndExam(Student student, Exam exam);
    List<Grade> findAllByStudentId(Long studentId);
    boolean existsByStudentIdAndExamId(Long studentId, Long examId);
}
