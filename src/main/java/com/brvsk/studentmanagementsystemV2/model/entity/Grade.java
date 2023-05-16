package com.brvsk.studentmanagementsystemV2.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Grade")
@Table(name = "grade")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    private Double value;
    @ManyToOne(fetch = FetchType.LAZY)
    private Exam exam;
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;
}
