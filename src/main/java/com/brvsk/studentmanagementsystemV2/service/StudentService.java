package com.brvsk.studentmanagementsystemV2.service;

import com.brvsk.studentmanagementsystemV2.auth.appuser.AppUser;
import com.brvsk.studentmanagementsystemV2.auth.appuser.AppUserRole;
import com.brvsk.studentmanagementsystemV2.auth.appuser.AppUserService;
import com.brvsk.studentmanagementsystemV2.auth.registration.EmailValidator;
import com.brvsk.studentmanagementsystemV2.auth.security.PasswordEncoder;
import com.brvsk.studentmanagementsystemV2.email.EmailSender;
import com.brvsk.studentmanagementsystemV2.exception.notFound.GroupNotFoundException;
import com.brvsk.studentmanagementsystemV2.exception.notFound.StudentNotFoundException;
import com.brvsk.studentmanagementsystemV2.mapper.ExamMapper;
import com.brvsk.studentmanagementsystemV2.mapper.StudentMapper;
import com.brvsk.studentmanagementsystemV2.model.dto.CourseDto;
import com.brvsk.studentmanagementsystemV2.model.dto.ExamDto;
import com.brvsk.studentmanagementsystemV2.model.dto.StudentDto;
import com.brvsk.studentmanagementsystemV2.model.entity.Course;
import com.brvsk.studentmanagementsystemV2.model.entity.Group;
import com.brvsk.studentmanagementsystemV2.model.entity.Student;
import com.brvsk.studentmanagementsystemV2.model.request.StudentRequest;
import com.brvsk.studentmanagementsystemV2.repository.GroupRepository;
import com.brvsk.studentmanagementsystemV2.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final StudentMapper studentMapper;
    private final ExamMapper examMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AppUserService appUserService;

    public String registerStudent(StudentRequest studentRequest) {
        Long groupId = studentRequest.getGroupId();
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException(groupId));

        String token = appUserService.signUpUser(
                new AppUser(
                        studentRequest.getFirstName(),
                        studentRequest.getLastName(),
                        studentRequest.getEmail(),
                        studentRequest.getPassword(),
                        AppUserRole.STUDENT
                )
        );

        Student student = toEntity(studentRequest);

        student.setGroup(group);
        studentRepository.save(student);
        return token;
    }

    public Long getStudentGroup(final Long studentId) {
        var student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
        return student.getGroup().getId();
    }

    public List<StudentDto> getAllStudents(){
        return studentMapper.toListDto(studentRepository.findAll());
    }

    public List<ExamDto> getStudentsExams(Long studentId){
        var student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
        var studentCourses = student.getGroup().getCourses();
        return studentCourses.stream()
                .flatMap(course -> course.getExams().stream())
                .map(examMapper::toDto)
                .toList();
    }

    public List<CourseDto> getStudentCourses(Long studentId){
        var student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
        var studentCourses = student.getGroup().getCourses();

        return studentCourses.stream()
                .map(studentMapper::toCourseDto)
                .toList();
    }


    private Student toEntity(StudentRequest studentRequest){
        return Student.builder()
                .firstName(studentRequest.getFirstName())
                .lastName(studentRequest.getLastName())
                .email(studentRequest.getEmail())
                .password(bCryptPasswordEncoder.encode(studentRequest.getPassword()))
                .appUserRole(AppUserRole.STUDENT)
                .enabled(false)
                .locked(false)
                .build();
    }
}
