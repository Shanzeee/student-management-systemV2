package com.brvsk.studentmanagementsystemV2.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Comparator;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Announcement")
@Table(name = "announcement")
public class Announcement implements Comparator<Announcement> {

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

    @Override
    public int compare(Announcement a1, Announcement a2) {
        if (isAnnouncementImportant(a1.getAnnouncementType()) && !isAnnouncementImportant(a2.getAnnouncementType())){
            return -1;
        } else if (!isAnnouncementImportant(a1.getAnnouncementType()) && isAnnouncementImportant(a2.getAnnouncementType())){
            return 1;
        }
        return a1.getPostedAt().compareTo(a2.getPostedAt());
    }

    private boolean isAnnouncementImportant(AnnouncementType announcementType){
        return announcementType.equals(AnnouncementType.IMPORTANT_INFO) ||
                announcementType.equals(AnnouncementType.RESCHEDULING) ||
                announcementType.equals(AnnouncementType.EXAM);
    }
}
