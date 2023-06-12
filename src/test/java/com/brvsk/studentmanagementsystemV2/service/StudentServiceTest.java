package com.brvsk.studentmanagementsystemV2.service;

import com.brvsk.studentmanagementsystemV2.auth.appuser.AppUser;
import com.brvsk.studentmanagementsystemV2.auth.appuser.AppUserRole;
import com.brvsk.studentmanagementsystemV2.auth.appuser.AppUserService;
import com.brvsk.studentmanagementsystemV2.exception.notFound.GroupNotFoundException;
import com.brvsk.studentmanagementsystemV2.exception.notFound.StudentNotFoundException;
import com.brvsk.studentmanagementsystemV2.mapper.ExamMapper;
import com.brvsk.studentmanagementsystemV2.mapper.StudentMapper;
import com.brvsk.studentmanagementsystemV2.model.dto.CourseDto;
import com.brvsk.studentmanagementsystemV2.model.dto.ExamDto;
import com.brvsk.studentmanagementsystemV2.model.dto.StudentDto;
import com.brvsk.studentmanagementsystemV2.model.entity.Course;
import com.brvsk.studentmanagementsystemV2.model.entity.Exam;
import com.brvsk.studentmanagementsystemV2.model.entity.Group;
import com.brvsk.studentmanagementsystemV2.model.entity.Student;
import com.brvsk.studentmanagementsystemV2.model.request.StudentRequest;
import com.brvsk.studentmanagementsystemV2.repository.GroupRepository;
import com.brvsk.studentmanagementsystemV2.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private StudentMapper studentMapper;

    @Mock
    private ExamMapper examMapper;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private AppUserService appUserService;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void registerStudent_InvalidGroupId_ThrowsGroupNotFoundException() {
        // Given
        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setGroupId(1L);

        // When
        when(groupRepository.findById(1L)).thenReturn(Optional.empty());

        // Then
        assertThrows(GroupNotFoundException.class, () -> studentService.registerStudent(studentRequest));
    }

    @Test
    public void getStudentGroup_ExistingStudent_ReturnsGroupId() {
        // Given
        Long studentId = 1L;
        Student student = new Student();
        student.setId(studentId);

        Group group = new Group();
        group.setId(1L);
        student.setGroup(group);

        // When
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        // Then
        Long groupId = studentService.getStudentGroup(studentId);
        assertEquals(group.getId(), groupId);
    }

    @Test
    public void getStudentGroup_NonExistingStudent_ThrowsStudentNotFoundException() {
        // Given
        Long studentId = 1L;

        // When
        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        // Then
        assertThrows(StudentNotFoundException.class, () -> studentService.getStudentGroup(studentId));
    }

    @Test
    public void getAllStudents_StudentsExist_ReturnsListOfStudents() {
        // Given
        List<Student> students = new ArrayList<>();
        students.add(new Student());
        students.add(new Student());

        List<StudentDto> expectedStudentDtos = new ArrayList<>();
        expectedStudentDtos.add(new StudentDto());
        expectedStudentDtos.add(new StudentDto());

        // When
        when(studentRepository.findAll()).thenReturn(students);
        when(studentMapper.toListDto(students)).thenReturn(expectedStudentDtos);

        // Then
        List<StudentDto> studentDtos = studentService.getAllStudents();
        assertEquals(expectedStudentDtos, studentDtos);
    }

    @Test
    public void getStudentsExams_ExistingStudent_ReturnsListOfExams() {
        // Given
        Long studentId = 1L;
        Student student = new Student();
        student.setId(studentId);
        Group group = new Group();
        student.setGroup(group);

        Course course1 = new Course();
        course1.setId(1L);
        Exam exam1 = new Exam();
        exam1.setId(1L);
        course1.getExams().add(exam1);

        Course course2 = new Course();
        course2.setId(2L);
        Exam exam2 = new Exam();
        exam2.setId(2L);
        course2.getExams().add(exam2);

        student.getGroup().getCourses().add(course1);
        student.getGroup().getCourses().add(course2);

        List<ExamDto> expectedExamDtos = new ArrayList<>();
        expectedExamDtos.add(new ExamDto());
        expectedExamDtos.add(new ExamDto());

        // When
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(examMapper.toDto(any(Exam.class))).thenReturn(new ExamDto());

        // Then
        List<ExamDto> examDtos = studentService.getStudentsExams(studentId);
        assertEquals(expectedExamDtos, examDtos);
    }

    @Test
    public void getStudentsExams_NonExistingStudent_ThrowsStudentNotFoundException() {
        // Given
        Long studentId = 1L;

        // When
        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        // Then
        assertThrows(StudentNotFoundException.class, () -> studentService.getStudentsExams(studentId));
    }

    @Test
    public void getStudentCourses_ExistingStudent_ReturnsListOfCourseDtos() {
        // Given
        Long studentId = 1L;
        Student student = new Student();
        student.setId(studentId);

        Course course1 = new Course();
        course1.setId(1L);

        Course course2 = new Course();
        course2.setId(2L);

        Group group = new Group();
        student.setGroup(group);

        student.getGroup().getCourses().add(course1);
        student.getGroup().getCourses().add(course2);

        List<CourseDto> expectedCourseDtos = new ArrayList<>();
        expectedCourseDtos.add(new CourseDto());
        expectedCourseDtos.add(new CourseDto());

        // When
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(studentMapper.toCourseDto(any(Course.class))).thenReturn(new CourseDto());

        // Then
        List<CourseDto> courseDtos = studentService.getStudentCourses(studentId);
        assertEquals(expectedCourseDtos, courseDtos);
    }

    @Test
    public void getStudentCourses_NonExistingStudent_ThrowsStudentNotFoundException() {
        // Given
        Long studentId = 1L;

        // When
        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        // Then
        assertThrows(StudentNotFoundException.class, () -> studentService.getStudentCourses(studentId));
    }

    private Student createStudent(){
        return Student.builder()
                .firstName("Wiktor")
                .lastName("Gora")
                .id(1L)
                .appUserRole(AppUserRole.STUDENT)
                .locked(false)
                .email("wiktor@gm.com")
                .enabled(true)
                .build();
    }

}
