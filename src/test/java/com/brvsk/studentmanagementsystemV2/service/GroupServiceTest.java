package com.brvsk.studentmanagementsystemV2.service;

import com.brvsk.studentmanagementsystemV2.exception.BadRequestException;
import com.brvsk.studentmanagementsystemV2.exception.notFound.GroupNotFoundException;
import com.brvsk.studentmanagementsystemV2.exception.notFound.NotFoundException;
import com.brvsk.studentmanagementsystemV2.mapper.GroupMapper;
import com.brvsk.studentmanagementsystemV2.model.dto.GroupDto;
import com.brvsk.studentmanagementsystemV2.model.entity.Department;
import com.brvsk.studentmanagementsystemV2.model.entity.Group;
import com.brvsk.studentmanagementsystemV2.model.request.GroupRequest;
import com.brvsk.studentmanagementsystemV2.repository.DepartmentRepository;
import com.brvsk.studentmanagementsystemV2.repository.GroupRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class GroupServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private GroupMapper groupMapper;

    @InjectMocks
    private GroupService groupService;

    @Test
    public void addGroup_GroupNameExists_ThrowsBadRequestException() {
        // Przygotowanie danych testowych
        GroupRequest groupRequest = groupRequestBuilder();
        groupRequest.setGroupName("GroupA");

        // Konfiguracja zachowania mocka
        when(groupRepository.existsByGroupName("GroupA")).thenReturn(true);

        // Wywołanie metody i sprawdzenie czy wyjątek został rzucony
        assertThrows(BadRequestException.class, () -> groupService.addGroup(groupRequest));
    }

    @Test
    public void addGroup_DepartmentNotFound_ThrowsNotFoundException() {
        // Przygotowanie danych testowych
        GroupRequest groupRequest = groupRequestBuilder();
        groupRequest.setGroupName("GroupA");
        groupRequest.setDepartmentShortcut("IT");

        // Konfiguracja zachowania mocka
        when(groupRepository.existsByGroupName("GroupA")).thenReturn(false);
        when(departmentRepository.findDepartmentByShortcut("IT")).thenReturn(Optional.empty());

        // Wywołanie metody i sprawdzenie czy wyjątek został rzucony
        assertThrows(NotFoundException.class, () -> groupService.addGroup(groupRequest));
    }

    @Test
    public void addGroup_ValidGroupRequest_GroupSaved() {
        // Przygotowanie danych testowych
        GroupRequest groupRequest = groupRequestBuilder();
        groupRequest.setGroupName("GroupA");
        groupRequest.setDepartmentShortcut("IT");

        // Konfiguracja zachowania mocka
        when(groupRepository.existsByGroupName("GroupA")).thenReturn(false);
        when(departmentRepository.findDepartmentByShortcut("IT")).thenReturn(Optional.of(new Department()));

        // Wywołanie metody
        groupService.addGroup(groupRequest);

        // Weryfikacja czy metody save zostały wywołane
        verify(groupRepository, times(1)).save(any(Group.class));
        verify(departmentRepository, times(1)).save(any(Department.class));
    }

    @Test
    public void getAllGroups_NoGroups_ReturnsEmptyList() {
        // Konfiguracja zachowania mocka
        when(groupRepository.findAll()).thenReturn(new ArrayList<>());

        // Wywołanie metody
        List<GroupDto> groups = groupService.getAllGroups();

        // Sprawdzenie czy zwrócona lista jest pusta
        assertTrue(groups.isEmpty());
    }

    @Test
    public void deleteGroup_GroupNotFound_ThrowsGroupNotFoundException() {
        // Przygotowanie danych testowych
        Long groupId = 1L;

        // Konfiguracja zachowania mocka
        when(groupRepository.existsById(groupId)).thenReturn(false);

        // Wywołanie metody i sprawdzenie czy wyjątek został rzucony
        assertThrows(GroupNotFoundException.class, () -> groupService.deleteGroup(groupId));
    }

    @Test
    public void deleteGroup_GroupFound_GroupDeleted() {
        // Przygotowanie danych testowych
        Long groupId = 1L;

        // Konfiguracja zachowania mocka
        when(groupRepository.existsById(groupId)).thenReturn(true);

        // Wywołanie metody
        groupService.deleteGroup(groupId);

        // Weryfikacja czy metoda deleteById została wywołana
        verify(groupRepository, times(1)).deleteById(groupId);
    }

    private GroupRequest groupRequestBuilder(){
        return GroupRequest.builder()
                .groupName("AiR")
                .departmentShortcut("WIMIR")
                .build();
    }

    private Group mapToEntity(GroupRequest groupRequest){
        return Group.builder()
                .groupName(groupRequest.getGroupName())
                .build();
    }
}
