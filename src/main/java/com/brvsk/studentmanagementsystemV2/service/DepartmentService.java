package com.brvsk.studentmanagementsystemV2.service;

import com.brvsk.studentmanagementsystemV2.exception.BadRequestException;
import com.brvsk.studentmanagementsystemV2.mapper.DepartmentMapper;
import com.brvsk.studentmanagementsystemV2.model.dto.DepartmentDto;
import com.brvsk.studentmanagementsystemV2.model.entity.Department;
import com.brvsk.studentmanagementsystemV2.model.request.DepartmentRequest;
import com.brvsk.studentmanagementsystemV2.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

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

    public List<DepartmentDto> getAllDepartments() {
        return departmentRepository
                .findAll()
                .stream()
                .map(departmentMapper::toDto)
                .collect(Collectors.toList());
    }

    private Department buildDepartment(DepartmentRequest departmentRequest){
        return Department.builder()
                .name(departmentRequest.getName())
                .shortcut(departmentRequest.getShortcut())
                .build();
    }


}
