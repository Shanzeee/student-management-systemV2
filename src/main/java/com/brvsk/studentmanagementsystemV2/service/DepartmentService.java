package com.brvsk.studentmanagementsystemV2.service;

import com.brvsk.studentmanagementsystemV2.exception.BadRequestException;
import com.brvsk.studentmanagementsystemV2.mapper.DepartmentMapper;
import com.brvsk.studentmanagementsystemV2.mapper.GroupMapper;
import com.brvsk.studentmanagementsystemV2.mapper.StudentMapper;
import com.brvsk.studentmanagementsystemV2.model.dto.DepartmentDto;
import com.brvsk.studentmanagementsystemV2.model.dto.GroupDto;
import com.brvsk.studentmanagementsystemV2.model.dto.StudentDto;
import com.brvsk.studentmanagementsystemV2.model.entity.Department;
import com.brvsk.studentmanagementsystemV2.model.request.DepartmentRequest;
import com.brvsk.studentmanagementsystemV2.repository.DepartmentRepository;
import com.brvsk.studentmanagementsystemV2.repository.GroupRepository;
import com.brvsk.studentmanagementsystemV2.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public void addDepartment(DepartmentRequest departmentRequest){
        if(departmentRepository.existsByName(departmentRequest.getName())){
            throw new BadRequestException(String.format("department with name %s already exists", departmentRequest.getName()));
        }
        if (departmentRepository.existsByShortcut(departmentRequest.getShortcut())){
            throw new BadRequestException(String.format("department with shortcut %s already exists", departmentRequest.getShortcut()));
        }

        Department newDepartment = buildDepartment(departmentRequest);

        departmentRepository.save(newDepartment);
    }

    public List<DepartmentDto> getAllDepartmentsWithGroups() {
        return departmentRepository
                .findAll()
                .stream()
                .map(departmentMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<GroupDto> getGroupsForDepartment(Long departmentId){
        return groupRepository.findAllByDepartment_Id(departmentId)
                .stream()
                .map(groupMapper::toDto)
                .toList();
    }

    public List<StudentDto> getStudentsForDepartment(Long departmentId){
        return studentRepository.findAllByGroupDepartment_Id(departmentId)
                .stream()
                .map(studentMapper::toDto)
                .toList();
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
    private Department buildDepartment(DepartmentRequest departmentRequest){
        return Department.builder()
                .name(departmentRequest.getName())
                .shortcut(departmentRequest.getShortcut())
                .build();
    }


}
