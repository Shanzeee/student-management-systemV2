package com.brvsk.studentmanagementsystemV2.repository;

import com.brvsk.studentmanagementsystemV2.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

}
