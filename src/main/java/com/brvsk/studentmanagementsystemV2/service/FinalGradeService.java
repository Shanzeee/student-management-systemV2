package com.brvsk.studentmanagementsystemV2.service;

import com.brvsk.studentmanagementsystemV2.exception.StudentNotAssignedToCourseException;
import com.brvsk.studentmanagementsystemV2.exception.notFound.CourseNotFoundException;
import com.brvsk.studentmanagementsystemV2.exception.notFound.StudentNotFoundException;
import com.brvsk.studentmanagementsystemV2.model.dto.FinalGradeDto;
import com.brvsk.studentmanagementsystemV2.model.entity.Course;
import com.brvsk.studentmanagementsystemV2.model.entity.FinalGrade;
import com.brvsk.studentmanagementsystemV2.model.entity.Student;
import com.brvsk.studentmanagementsystemV2.model.request.FinalGradeRequest;
import com.brvsk.studentmanagementsystemV2.repository.CourseRepository;
import com.brvsk.studentmanagementsystemV2.repository.FinalGradeRepository;
import com.brvsk.studentmanagementsystemV2.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class FinalGradeService {

    private final FinalGradeRepository finalGradeRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public List<FinalGradeDto> getStudentFinalGrades(Long studentId){
        var student = studentRepository.findById(studentId)
                .orElseThrow( () -> new StudentNotFoundException(studentId));
        var studentCourses = student.getGroup().getCourses();
        var finalGrades = new ArrayList<FinalGradeDto>(studentCourses.size());

        studentCourses
                .forEach(course -> {
                    FinalGrade finalGrade = finalGradeRepository.findByStudentIdAndCourseId(studentId, course.getId());
                    var finalGradeValue = 0.00;
                    if (finalGrade != null){
                        finalGradeValue = finalGrade.getValue();
                    }
                    var finalGradeDto = FinalGradeDto.builder()
                            .courseName(course.getName())
                            .value(finalGradeValue)
                            .teacherName(course.getTeacher().getFirstName() +" "+ course.getTeacher().getLastName())
                            .build();
                    finalGrades.add(finalGradeDto);
                });
        return finalGrades;
    }

    @Transactional
    public void addFinalGrade(FinalGradeRequest finalGradeRequest){
        var student = studentRepository.findById(finalGradeRequest.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException(finalGradeRequest.getStudentId()));

        var course = courseRepository.findById((finalGradeRequest.getCourseId()))
                .orElseThrow(() -> new CourseNotFoundException(finalGradeRequest.getCourseId()));

        validateStudentAssignedToCourse(student, course);
        FinalGrade finalGrade = finalGradeRepository.findByStudentIdAndCourseId(finalGradeRequest.getStudentId(), finalGradeRequest.getCourseId());
        if (finalGrade != null){
            finalGrade.setValue(finalGradeRequest.getValue());
            finalGradeRepository.save(finalGrade);
        }else {
            var newFinalGrade = mapToEntity(finalGradeRequest);
            newFinalGrade.setStudent(student);
            newFinalGrade.setCourse(course);
            finalGradeRepository.save(newFinalGrade);
        }
    }

    private FinalGrade mapToEntity(FinalGradeRequest finalGradeRequest){

        return FinalGrade.builder()
                .value(finalGradeRequest.getValue())
                .build();
    }

    private void validateStudentAssignedToCourse(Student student, Course course){
        List<Course> studentCourses = student.getGroup().getCourses();

        boolean isAssignedToCourse = studentCourses.stream()
                .anyMatch(c -> Objects.equals(c.getId(), course.getId()));
        if (!isAssignedToCourse){
            throw new StudentNotAssignedToCourseException(student.getId(), course.getId());
        }
    }
}
