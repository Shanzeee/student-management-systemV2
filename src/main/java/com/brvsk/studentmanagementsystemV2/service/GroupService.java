package com.brvsk.studentmanagementsystemV2.service;

import com.brvsk.studentmanagementsystemV2.exception.notFound.DepartmentNotFoundException;
import com.brvsk.studentmanagementsystemV2.mapper.GroupMapper;
import com.brvsk.studentmanagementsystemV2.model.dto.GroupDto;
import com.brvsk.studentmanagementsystemV2.model.entity.Department;
import com.brvsk.studentmanagementsystemV2.model.entity.Group;
import com.brvsk.studentmanagementsystemV2.model.request.GroupRequest;
import com.brvsk.studentmanagementsystemV2.repository.DepartmentRepository;
import com.brvsk.studentmanagementsystemV2.repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final DepartmentRepository departmentRepository;
    private final GroupMapper groupMapper;

    public void addGroup(GroupRequest groupRequest){

        Long departmentId = departmentRepository
                .getDepartmentByShortcut(groupRequest.getDepartmentShortcut())
                .getId();
        departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException(departmentId));

        Group newGroup = mapToEntity(groupRequest);
        Department department = departmentRepository.getReferenceById(departmentId);

        newGroup.setDepartment(department);
        groupRepository.save(newGroup);
        departmentRepository.save(department);
    }

    public List<GroupDto> getAllGroups(){
        return groupRepository
                .findAll()
                .stream()
                .map(groupMapper::toDto)
                .collect(Collectors.toList());
    }

    private Group mapToEntity(GroupRequest groupRequest){
        return Group.builder()
                .groupName(groupRequest.getGroupName())
                .build();
    }

}
