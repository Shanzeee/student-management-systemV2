package com.brvsk.studentmanagementsystemV2.repository;

import com.brvsk.studentmanagementsystemV2.model.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
}
