package com.brvsk.studentmanagementsystemV2.service;

import com.brvsk.studentmanagementsystemV2.mapper.StudentMapper;
import com.brvsk.studentmanagementsystemV2.mapper.TeacherMapper;
import com.brvsk.studentmanagementsystemV2.model.dto.StudentDto;
import com.brvsk.studentmanagementsystemV2.model.dto.TeacherDto;
import com.brvsk.studentmanagementsystemV2.model.entity.Teacher;
import com.brvsk.studentmanagementsystemV2.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    public Teacher addTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public List<TeacherDto> getAllTeachers(){
        return teacherMapper.toListDto(teacherRepository.findAll());
    }


}
