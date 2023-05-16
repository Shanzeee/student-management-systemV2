package com.brvsk.studentmanagementsystemV2.repository;
import com.brvsk.studentmanagementsystemV2.model.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    boolean existsByName(String name);
    boolean existsByShortcut(String shortcut);

    Department getDepartmentByShortcut(String shortcut);
}
