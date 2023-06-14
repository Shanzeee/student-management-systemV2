package com.brvsk.studentmanagementsystemV2.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Announcement")
@Table(name = "announcement")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    private String title;
    private String message;
    @Enumerated(EnumType.STRING)
    private AnnouncementType announcementType;

    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    private Teacher createdBy;

    @ManyToOne
    private Course course;

    @CreationTimestamp
    private LocalDateTime postedAt;

}
