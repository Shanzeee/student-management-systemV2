package com.brvsk.studentmanagementsystemV2.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Course")
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @JsonIgnore
    @ManyToOne(
            cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE},
            fetch = FetchType.LAZY)
    private Group group;
    @OneToMany(mappedBy = "course")
    private List<Announcement> announcements = new ArrayList<>();
    @OneToMany(
            cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE},
            fetch = FetchType.LAZY)
    private Set<Exam> exams  = new HashSet<>();

    private String name;
    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    private Teacher teacher;
}
