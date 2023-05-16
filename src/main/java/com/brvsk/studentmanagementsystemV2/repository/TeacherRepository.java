package com.brvsk.studentmanagementsystemV2.repository;

import com.brvsk.studentmanagementsystemV2.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    boolean existsByEmail(String email);
}
