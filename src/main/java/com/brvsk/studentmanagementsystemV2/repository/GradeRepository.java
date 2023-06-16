package com.brvsk.studentmanagementsystemV2.repository;


import com.brvsk.studentmanagementsystemV2.model.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade,Long> {

    List<Grade> findAllByStudentIdAndExam_Course_Id(Long studentId, Long courseId);
    boolean existsByStudentIdAndExamId(Long studentId, Long examId);
}
