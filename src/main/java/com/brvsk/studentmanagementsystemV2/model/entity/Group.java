package com.brvsk.studentmanagementsystemV2.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Entity(name = "Groups")
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false
    )
    private  Long id;

    private String groupName;

    @OneToMany(
            mappedBy = "group",
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST
    )
    private List<Student> students = new ArrayList<>();

    @OneToMany(
            mappedBy = "group",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Course> courses = new ArrayList<>();

    @ManyToOne(
            cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY
    )
    private Department department;
}
