package com.brvsk.studentmanagementsystemV2.service;

import com.brvsk.studentmanagementsystemV2.model.entity.Teacher;
import com.brvsk.studentmanagementsystemV2.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public Teacher addTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

}
