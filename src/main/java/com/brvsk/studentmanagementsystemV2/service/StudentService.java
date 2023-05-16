package com.brvsk.studentmanagementsystemV2.service;

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

    public void addStudent(StudentRequest studentRequest) {
        Long groupId = studentRequest.getGroupId();
        Group group = groupRepository.getReferenceById(groupId);
        Student student = toEntity(studentRequest);

        student.setGroup(group);
        studentRepository.save(student);
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
