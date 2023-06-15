package com.brvsk.studentmanagementsystemV2.repository;

import com.brvsk.studentmanagementsystemV2.model.entity.FinalGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinalGradeRepository extends JpaRepository<FinalGrade, Long> {

    FinalGrade findByStudentIdAndCourseId(Long studentId, Long courseId);
}
