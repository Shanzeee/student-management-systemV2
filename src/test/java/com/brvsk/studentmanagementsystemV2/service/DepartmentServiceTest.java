package com.brvsk.studentmanagementsystemV2.service;

import com.brvsk.studentmanagementsystemV2.exception.BadRequestException;
import com.brvsk.studentmanagementsystemV2.model.entity.Department;
import com.brvsk.studentmanagementsystemV2.model.request.DepartmentRequest;
import com.brvsk.studentmanagementsystemV2.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class DepartmentServiceTest {

    @BeforeEach
    void setUp() {
    }

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @Test
    public void addDepartment_DepartmentNameExists_ThrowsBadRequestException() {
        // Given
        DepartmentRequest departmentRequest = departmentRequestBuilder();
        departmentRequest.setName("IT");

        // When
        when(departmentRepository.existsByName("IT")).thenReturn(true);

        //Then
        assertThrows(BadRequestException.class, () -> departmentService.addDepartment(departmentRequest));
    }
    @Test
    public void addDepartment_DepartmentShortcutExists_ThrowsBadRequestException() {
        // Given
        DepartmentRequest departmentRequest = departmentRequestBuilder();
        departmentRequest.setShortcut("IT");

        // When
        when(departmentRepository.existsByShortcut("IT")).thenReturn(true);

        //Then
        assertThrows(BadRequestException.class, () -> departmentService.addDepartment(departmentRequest));
    }

    @Test
    public void addDepartment_ValidDepartmentRequest_DepartmentSaved() {
        // Given
        DepartmentRequest departmentRequest = departmentRequestBuilder();
        departmentRequest.setName("IT");
        departmentRequest.setShortcut("IT");

        // When
        when(departmentRepository.existsByName("IT")).thenReturn(false);
        when(departmentRepository.existsByShortcut("IT")).thenReturn(false);

        departmentService.addDepartment(departmentRequest);

        //Then
        verify(departmentRepository, times(1)).save(any(Department.class));
    }

    private DepartmentRequest departmentRequestBuilder(){
        return DepartmentRequest.builder()
                .name("IT")
                .shortcut("IT")
                .build();
    }
}