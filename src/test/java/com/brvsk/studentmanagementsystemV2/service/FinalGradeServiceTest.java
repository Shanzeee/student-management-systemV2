package com.brvsk.studentmanagementsystemV2.service;

import com.brvsk.studentmanagementsystemV2.model.dto.FinalGradeDto;
import com.brvsk.studentmanagementsystemV2.model.entity.*;
import com.brvsk.studentmanagementsystemV2.model.request.FinalGradeRequest;
import com.brvsk.studentmanagementsystemV2.repository.CourseRepository;
import com.brvsk.studentmanagementsystemV2.repository.FinalGradeRepository;
import com.brvsk.studentmanagementsystemV2.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FinalGradeServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private FinalGradeRepository finalGradeRepository;

    @InjectMocks
    private FinalGradeService finalGradeService;

    @Captor
    private ArgumentCaptor<FinalGrade> finalGradeCaptor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddFinalGrade_ExistingFinalGrade_UpdateFinalGradeValue() {
        // Given
        Group group = createGroup();
        Student student = creatStudent();
        student.setGroup(group);
        group.setStudents(List.of(student));

        Course course = createCourse(1L);
        Course course1 = createCourse(2L);

        group.setCourses(List.of(course, course1));

        Double updatedValue = 5.0;

        FinalGradeRequest finalGradeRequest = new FinalGradeRequest(student.getId(), course.getId(), updatedValue);


        FinalGrade existingFinalGrade = new FinalGrade(1L, student, course,3.5, LocalDateTime.now());


        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        when(courseRepository.findById(course.getId())).thenReturn(Optional.of(group.getCourses().get(1)));
        when(finalGradeRepository.findByStudentIdAndCourseId(student.getId(), course.getId())).thenReturn(existingFinalGrade);
        // When
        finalGradeService.addFinalGrade(finalGradeRequest);

        // Then
        verify(finalGradeRepository).save(finalGradeCaptor.capture());
        FinalGrade savedFinalGrade = finalGradeCaptor.getValue();
        Assertions.assertEquals(updatedValue, savedFinalGrade.getValue());
    }

    @Test
    public void testAddFinalGrade_NonExistingFinalGrade_CreateNewFinalGrade() {
        // Given
        Group group = createGroup();
        Student student = creatStudent();
        student.setGroup(group);
        group.setStudents(List.of(student));

        Course course = createCourse(1L);
        Course course1 = createCourse(2L);

        group.setCourses(List.of(course, course1));

        Double updatedValue = 5.0;

        FinalGradeRequest finalGradeRequest = new FinalGradeRequest(student.getId(), course.getId(), updatedValue);

        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        when(courseRepository.findById(course.getId())).thenReturn(Optional.of(group.getCourses().get(1)));
        when(finalGradeRepository.findByStudentIdAndCourseId(student.getId(), course.getId())).thenReturn(null);
        // When
        finalGradeService.addFinalGrade(finalGradeRequest);

        // Then
        verify(finalGradeRepository).save(finalGradeCaptor.capture());
        FinalGrade savedFinalGrade = finalGradeCaptor.getValue();
        Assertions.assertEquals(updatedValue, savedFinalGrade.getValue());
    }

    @Test
    public void testGetStudentFinalGrades_StudentExists_ReturnsStudentFinalGrades() {
        // Given
        Group group = createGroup();

        Student student = creatStudent();
        group.setStudents(List.of(student));
        student.setGroup(group);

        Course course1 = createCourse(1L);
        Course course2 = createCourse(2L);
        group.setCourses(List.of(course1, course2));



        FinalGrade finalGrade1 = createFinalGrade(4.0);
        finalGrade1.setStudent(student);
        finalGrade1.setCourse(course1);

        FinalGrade finalGrade2 = createFinalGrade(3.5);
        finalGrade2.setStudent(student);
        finalGrade2.setCourse(course2);

        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        when(finalGradeRepository.findByStudentIdAndCourseId(student.getId(), course1.getId())).thenReturn(finalGrade1);
        when(finalGradeRepository.findByStudentIdAndCourseId(student.getId(), course2.getId())).thenReturn(finalGrade2);

        // When
        List<FinalGradeDto> result = finalGradeService.getStudentFinalGrades(student.getId());

        // Then
        Assertions.assertEquals(2, result.size());

        FinalGradeDto finalGradeDto1 = result.get(0);
        Assertions.assertEquals(course1.getTeacher().getFirstName() +" "+course1.getTeacher().getLastName(), finalGradeDto1.getTeacherName());
        Assertions.assertEquals(course1.getName(), finalGradeDto1.getCourseName());
        Assertions.assertEquals(4.0, finalGradeDto1.getValue());
        // Add more assertions for other properties

        FinalGradeDto finalGradeDto2 = result.get(1);
        Assertions.assertEquals(course2.getTeacher().getFirstName() +" "+course2.getTeacher().getLastName(), finalGradeDto2.getTeacherName());
        Assertions.assertEquals(course2.getName(), finalGradeDto2.getCourseName());
        Assertions.assertEquals(3.5, finalGradeDto2.getValue());
        // Add more assertions for other properties
    }

    private FinalGrade createFinalGrade(Double value){
        return FinalGrade.builder()
                .value(value)
                .build();
    }

    private Teacher createTeacher(){
        return Teacher.builder()
                .firstName("michal")
                .lastName("choinacki")
                .build();
    }

    private Course createCourse(Long courseId){
        return Course.builder()
                .id(courseId)
                .name("Autmatyka")
                .teacher(createTeacher())
                .build();
    }

    private Group createGroup(){
        return Group.builder()
                .id(1L)
                .groupName("Autmatyka i robotyka")
                .department(new Department())
                .build();
    }

    private Student creatStudent(){
        return Student.builder()
                .id(1L)
                .firstName("Kacper")
                .lastName("bwdas")
                .enabled(true)
                .email("kacper@gmail.com")
                .build();
    }

}




