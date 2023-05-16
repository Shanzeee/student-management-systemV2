package com.brvsk.studentmanagementsystemV2.service;

import com.brvsk.studentmanagementsystemV2.model.entity.Student;
import com.brvsk.studentmanagementsystemV2.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long userId) {
        return studentRepository.getReferenceById(userId);
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }
}
