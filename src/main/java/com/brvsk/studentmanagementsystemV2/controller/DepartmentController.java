package com.brvsk.studentmanagementsystemV2.controller;

import com.brvsk.studentmanagementsystemV2.model.dto.GroupDto;
import com.brvsk.studentmanagementsystemV2.model.dto.StudentDto;
import com.brvsk.studentmanagementsystemV2.model.entity.Department;
import com.brvsk.studentmanagementsystemV2.model.request.DepartmentRequest;
import com.brvsk.studentmanagementsystemV2.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService departmentService;

    @GetMapping
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @PostMapping
    public ResponseEntity<String> createDepartment(@RequestBody @Valid DepartmentRequest departmentRequest){
        departmentService.addDepartment(departmentRequest);
        return new ResponseEntity<>("A new department " + departmentRequest.getName() + " has been added", HttpStatus.CREATED);
    }

    @GetMapping
    @RequestMapping("/{departmentId}/students")
    public List<StudentDto> getStudentsForDepartment(@PathVariable Long departmentId){
        return departmentService.getStudentsForDepartment(departmentId);
    }

    @GetMapping
    @RequestMapping("/{departmentId}/groups")
    public List<GroupDto> getGroupsForDepartment(@PathVariable Long departmentId){
        return departmentService.getGroupsForDepartment(departmentId);
    }
}
