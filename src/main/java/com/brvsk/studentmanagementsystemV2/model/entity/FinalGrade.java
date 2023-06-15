package com.brvsk.studentmanagementsystemV2.model.entity;

import com.brvsk.studentmanagementsystemV2.utils.Grade;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "FinalGrade")
@Table(name = "final_grade")
public class FinalGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;

    @Grade
    private Double value;

    @CreationTimestamp
    @Column(
            name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime createdAt;

}



