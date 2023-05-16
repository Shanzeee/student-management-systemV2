package com.brvsk.studentmanagementsystemV2.repository;

import com.brvsk.studentmanagementsystemV2.model.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ExamRepository extends JpaRepository<Exam,Long> {
    boolean existsByStartsAt(LocalDateTime time);
}
