package com.brvsk.studentmanagementsystemV2.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Department")
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false
    )
    private  Long id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @Column(
            name = "shortcut",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String shortcut;

    @OneToMany(
            mappedBy = "department",
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List< Group> groups;
}
