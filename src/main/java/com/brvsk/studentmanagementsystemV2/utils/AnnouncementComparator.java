package com.brvsk.studentmanagementsystemV2.utils;

import com.brvsk.studentmanagementsystemV2.model.dto.AnnouncementDto;
import com.brvsk.studentmanagementsystemV2.model.entity.Announcement;
import com.brvsk.studentmanagementsystemV2.model.entity.AnnouncementType;

import java.util.Comparator;

public class AnnouncementComparator implements Comparator<Announcement> {

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
