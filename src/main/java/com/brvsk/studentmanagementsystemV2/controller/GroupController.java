package com.brvsk.studentmanagementsystemV2.controller;

import com.brvsk.studentmanagementsystemV2.model.entity.Group;
import com.brvsk.studentmanagementsystemV2.model.request.GroupRequest;
import com.brvsk.studentmanagementsystemV2.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/groups")
@AllArgsConstructor
public class GroupController {

    private final GroupService groupService;


    @GetMapping
    public List<Group> getAllGroups(){
        return groupService.getAllGroups();
    }

    @PostMapping
    public ResponseEntity<String> createNewGroup(@Valid @RequestBody GroupRequest groupRequest){
        groupService.addGroup(groupRequest);
        return new ResponseEntity<>("A new group " +groupRequest.getGroupName()+ " has been added", HttpStatus.CREATED);
    }

}
