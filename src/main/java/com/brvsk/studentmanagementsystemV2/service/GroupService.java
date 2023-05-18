package com.brvsk.studentmanagementsystemV2.service;

import com.brvsk.studentmanagementsystemV2.exception.BadRequestException;
import com.brvsk.studentmanagementsystemV2.exception.notFound.DepartmentNotFoundException;
import com.brvsk.studentmanagementsystemV2.exception.notFound.GroupNotFoundException;
import com.brvsk.studentmanagementsystemV2.exception.notFound.NotFoundException;
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

        if (groupRepository.existsByGroupName(groupRequest.getGroupName())){
            throw new BadRequestException("Group with this name already exists");
        }

        Department department = departmentRepository
                .findDepartmentByShortcut(groupRequest.getDepartmentShortcut())
                .orElseThrow(() ->
                        new NotFoundException
                                ("department with shortcut " +groupRequest.getDepartmentShortcut()+ " not found"));


        Group newGroup = mapToEntity(groupRequest);

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

    public void deleteGroup(Long groupId){
        if (!groupRepository.existsById(groupId)){
            throw new GroupNotFoundException(groupId);
        }
        groupRepository.deleteById(groupId);
    }

    private Group mapToEntity(GroupRequest groupRequest){
        return Group.builder()
                .groupName(groupRequest.getGroupName())
                .build();
    }

}
