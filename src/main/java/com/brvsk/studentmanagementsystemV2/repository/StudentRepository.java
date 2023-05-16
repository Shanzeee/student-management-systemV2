package com.brvsk.studentmanagementsystemV2.repository;

import com.brvsk.studentmanagementsystemV2.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmail(String email);
    List<Student> findAllByGroup_GroupId(Long groupId);
    Student findByEmail(String email);
}
