package com.brvsk.studentmanagementsystemV2.service;

import com.brvsk.studentmanagementsystemV2.exception.notFound.GroupNotFoundException;
import com.brvsk.studentmanagementsystemV2.exception.notFound.TeacherNotFoundException;
import com.brvsk.studentmanagementsystemV2.mapper.CourseMapper;
import com.brvsk.studentmanagementsystemV2.model.dto.CourseDto;
import com.brvsk.studentmanagementsystemV2.model.entity.Course;
import com.brvsk.studentmanagementsystemV2.model.entity.Gender;
import com.brvsk.studentmanagementsystemV2.model.entity.Group;
import com.brvsk.studentmanagementsystemV2.model.entity.Teacher;
import com.brvsk.studentmanagementsystemV2.model.request.CourseRequest;
import com.brvsk.studentmanagementsystemV2.repository.CourseRepository;
import com.brvsk.studentmanagementsystemV2.repository.GroupRepository;
import com.brvsk.studentmanagementsystemV2.repository.TeacherRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CourseServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseMapper courseMapper;

    @InjectMocks
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void itShouldAddCourse() {
        // Given
        Teacher teacher = createTeacher();
        Group group = createGroup();
        Course newCourse = new Course();
        newCourse.setTeacher(teacher);
        newCourse.setGroup(group);
        newCourse.setExams(null);

        CourseRequest courseRequest = new CourseRequest();
        courseRequest.setTeacherId(teacher.getId());
        courseRequest.setGroupId(group.getId());


        // When
        when(teacherRepository.findById(any(Long.class))).thenReturn(Optional.of(teacher));
        when(groupRepository.findById(any(Long.class))).thenReturn(Optional.of(group));
        when(courseRepository.save(any(Course.class))).thenReturn(newCourse);

        courseService.addCourse(courseRequest);

        // Then
        verify(teacherRepository, times(1)).findById(1L);
        verify(groupRepository, times(1)).findById(1L);
        verify(courseRepository, times(1)).save(newCourse);
    }

    @Test
    void addCourse_TeacherNotFound_ThrowsTeacherNotFoundException() {
        // Given
        CourseRequest courseRequest = new CourseRequest();
        courseRequest.setTeacherId(1L);
        courseRequest.setGroupId(2L);
        courseRequest.setName("Math");

        // When
        when(teacherRepository.findById(any(Long.class))).thenReturn(java.util.Optional.empty());

        assertThrows(TeacherNotFoundException.class, () -> courseService.addCourse(courseRequest));

        // Then
        verify(teacherRepository, times(1)).findById(1L);
        verify(groupRepository, never()).findById(any(Long.class));
        verify(courseRepository, never()).save(any(Course.class));
    }

    @Test
    void addCourse_GroupNotFound_ThrowsGroupNotFoundException() {
        // Given
        CourseRequest courseRequest = new CourseRequest();
        courseRequest.setTeacherId(1L);
        courseRequest.setGroupId(2L);
        courseRequest.setName("Math");

        // When
        when(teacherRepository.findById(any(Long.class))).thenReturn(Optional.of(new Teacher()));
        when(groupRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(GroupNotFoundException.class, () -> courseService.addCourse(courseRequest));

        // Then
        verify(teacherRepository, times(1)).findById(1L);
        verify(groupRepository, times(1)).findById(2L);
        verify(courseRepository, never()).save(any(Course.class));
    }

    @Test
    public void getAllCourses_ReturnsListOfCourseDto() {
        // Given
        Course course1 = new Course();
        course1.setId(1L);
        Course course2 = new Course();
        course2.setId(2L);
        List<Course> courses = Arrays.asList(course1, course2);

        CourseDto courseDto1 = new CourseDto();
        courseDto1.setId(1L);
        CourseDto courseDto2 = new CourseDto();
        courseDto2.setId(2L);
        List<CourseDto> expectedCourseDto = Arrays.asList(courseDto1, courseDto2);

        // When
        when(courseRepository.findAll()).thenReturn(courses);
        when(courseMapper.toDto(course1)).thenReturn(courseDto1);
        when(courseMapper.toDto(course2)).thenReturn(courseDto2);

        // Then
        List<CourseDto> actualCourseDto = courseService.getAllCourses();

        assertEquals(expectedCourseDto, actualCourseDto);
    }

    private Teacher createTeacher(){
        return Teacher.builder()
                .id(1L)
                .firstName("AMG")
                .lastName("GTR")
                .email("amg@gtr.com")
                .gender(Gender.OTHER)
                .build();
    }
    private Group createGroup(){
        return Group.builder()
                .groupName("Autmoatyka")
                .id(1L)
                .build();
    }
}
