package com.brvsk.studentmanagementsystemV2.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Exam")
@Table(name = "exam")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    private LocalDateTime startsAt;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    @OneToMany(
            mappedBy = "exam",
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @Fetch(FetchMode.JOIN)
    private List<Grade> grades = new ArrayList<>();
}
