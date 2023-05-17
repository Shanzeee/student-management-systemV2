package com.brvsk.studentmanagementsystemV2.service;

import com.brvsk.studentmanagementsystemV2.exception.notFound.StudentNotFoundException;
import com.brvsk.studentmanagementsystemV2.mapper.ExamMapper;
import com.brvsk.studentmanagementsystemV2.mapper.StudentMapper;
import com.brvsk.studentmanagementsystemV2.model.dto.ExamDto;
import com.brvsk.studentmanagementsystemV2.model.dto.StudentDto;
import com.brvsk.studentmanagementsystemV2.model.entity.Course;
import com.brvsk.studentmanagementsystemV2.model.entity.Group;
import com.brvsk.studentmanagementsystemV2.model.entity.Student;
import com.brvsk.studentmanagementsystemV2.model.request.StudentRequest;
import com.brvsk.studentmanagementsystemV2.repository.GroupRepository;
import com.brvsk.studentmanagementsystemV2.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final StudentMapper studentMapper;
    private final ExamMapper examMapper;

    public void addStudent(StudentRequest studentRequest) {
        Long groupId = studentRequest.getGroupId();
        Group group = groupRepository.getReferenceById(groupId);
        Student student = toEntity(studentRequest);

        student.setGroup(group);
        studentRepository.save(student);
    }

    public List<StudentDto> getAllStudents(){
        return studentMapper.toListDto(studentRepository.findAll());
    }

    public List<ExamDto> getStudentsExam(Long studentId){
        var student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
        List<Course> studentCourses = student.getGroup().getCourses();
        return studentCourses.stream()
                .flatMap(course -> course.getExams().stream())
                .map(examMapper::toDto)
                .toList();
    }

    private Student toEntity(StudentRequest studentRequest){
        return Student.builder()
                .firstName(studentRequest.getFirstName())
                .lastName(studentRequest.getLastName())
                .email(studentRequest.getEmail())
                .gender(studentRequest.getGender())
                .build();
    }
}
